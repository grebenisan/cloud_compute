#!/bin/bash
#--------------------------------------------------------------------------------------
# Usage:   ./$0   FileNum   [BytesChunk]
# Ex1:     ./$0   10        
# Ex2:     ./$0   10        100000000
#--------------------------------------------------------------------------------------

function Usage
{
	local bn=$(basename $0)
        printf "\n\n\n
Usage:  $bn [-h] [-a] [-c NNN] [-e NNN] [-m emails] [-L dir] [-P dir] [-R]     fileName

Where	-h = Help
	-a = Auto-Analyze Table	(Default: NOT auto-analyze)
	-c = ChunkSize		(Minium: 100000000 bytes).  The smaller this ChunkSize s the bigger executors will be used.
	-e = NumExecutors	(Excutors to use if FileSize is empty)
	-m = Mail List		(Email names to send emails)
	-L = RootProgDir	(To create Root Log Directory; Default= $G_ROOT_PROGDIR)
	-P = ProjectName	(To create DirectoryName, under RootLogDir)
	-R = Reset		(Remove all previous genereated scripts.  Start over)
	\n\n\n
	";
	exit 0;
} #function Usage



function Func_Trap_INT
{
	:
}



function RemoveOldFiles
{
	local daysOld="$1";
	local rootDir="$2";
	local itab1="$3";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";

	dateStr=$(date  -d "-$daysOld days" +%Y-%m-%d)

	printf "\n\n${itab1}==> function RemoveOldFiles;  daysOld=$daysOld; dateStr=\"$dateStr\"\n"

	printf "${itab2}A) List of old files (older than $daysOld) to be removed (under RootDir $rootDir):\n"
	find $rootDir  -type f    ! -newermt "$dateStr"    -exec ls -ltr {} \; 2>/dev/null
	printf "\n\n";

	printf "${itab2}B) Removing ... \n"
	find $rootDir  -type f ! -newermt "$dateStr" -exec rm {} \; 
	printf "\n\n";

	printf "${itab2}C) Remaining files AFTER removed:\n"
	find $rootDir  -type f   -exec ls -ltr {} \; 2>/dev/null
	printf "\n\n";

	printf "${itab1}<== function RemoveOldFiles\n\n"
}


function GetENVvariable
{
	local itab1="$1";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	printf "${itab1}==> function GetENVvariable\n"

	hdfs_cluster_nm=`hdfs getconf -confKey dfs.nameservices 2>/dev/null`
	local RC=$?
	if [[ $RC -ne 0 ]]
	then
		printf "\n\n\nERROR: Cannot execute \"hdfs  getconf\" ; RC=$RC\n\n\n"
		exit -1;
	fi;

	cluster_env=${hdfs_cluster_nm:5:3}

	if   [[ ${cluster_env} = 'dev' ]]
	then	env='dev'; ENV='DEV';
	elif [[ ${cluster_env} = 'tst' ]]
	then	env='tst'; ENV='TST';
	elif [[ ${cluster_env} = 'crt' ]]
	then	env='crt'; ENV='CRT';
	elif [[ ${cluster_env} = 'prd' ]]
	then	env='prd'; ENV='PRD';
	else	printf "\n\n\n\n\nERROR: I don't know what env I am on... Exiting\n\n\n\n\n"
	        exit 1
	fi;
	printf "${itab2}ENV=${ENV};  env=${env}\n"
	printf "${itab1}<== function GetENVvariable\n\n"
} #function GetENVvariable



function ValidateInputParameters
{
	local INPUT_FILENAME="$1";	#FileName (HDFS) containing list of tables to profile

	local FIRST_LETTER;

	local itab1="$2";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	printf "${itab1}==> function ValidateInputParameters\n"

	printf "${itab2}1) Check CHUNK_SIZE\n"
	if [[ $G_CHUNK_SIZE -lt 100000000 ]]
	then
		printf "\n\n\nWARNING:  Input Chunksize TOO SMALL ($G_CHUNK_SIZE bytes).";
		G_CHUNK_SIZE=$G_CHUNK_SIZE_DEFAULT
		printf "  Reset to default: $G_CHUNK_SIZE bytes\n\n\n"
	fi;
	if [[ $G_CHUNK_SIZE -gt 100000000000 ]]
	then
		printf "\n\n\nWARNING:  Input Chunksize TOO BIG ($G_CHUNK_SIZE bytes).";
		G_CHUNK_SIZE=$G_CHUNK_SIZE_DEFAULT
		printf "  Reset to default: $G_CHUNK_SIZE bytes\n\n\n"
	fi;


	printf "${itab2}2) Check NUM_EXECUTORS\n"
	if [[ $G_NUM_EXECUTORS -lt 2 ]]
	then
		printf "\n\n\nWARNING:  Input G_NUM_EXECUTORS TOO SMALL ($G_NUM_EXECUTORS bytes).";
		G_NUM_EXECUTORS=$G_NUM_EXECUTORS_DEFAULT
		printf "  Reset to default: $G_NUM_EXECUTORS executors\n\n\n"
	fi;
	if [[ $G_NUM_EXECUTORS -gt 200 ]]
	then
		printf "\n\n\nWARNING:  Input G_NUM_EXECUTORS TOO BIG ($G_NUM_EXECUTORS bytes).";
		G_NUM_EXECUTORS=$G_NUM_EXECUTORS_DEFAULT
		printf "  Reset to default: $G_NUM_EXECUTORS executors\n\n\n"
	fi;


	#Make sure user enter completes filename, not just a digit 
	#(so inputfilename could be anything instead of hard-code to same name)
	printf "${itab2}3) Check FILENAME\n"
	FIRST_LETTER=${INPUT_FILENAME:0:1}
	if [[ "$FIRST_LETTER" =~ [0-9] ]]
	then
		printf "\n\n\nERROR: First letter of Input file MUST NOT be a digit (Entered: $INPUT_FILENAME).\n\n\n"
		exit -1;
	fi;


	printf "${itab2}4) Check G_SPARK_MODE\n"
	if [[ $G_SPARK_MODE == "LOCAL" ]] || [[ $G_SPARK_MODE == "CLUSTER" ]]
	then
		:
	else
		printf "\n\n\nERROR: Invalid SPARK_MODE (\"$G_SPARK_MODE\").  Must be either:  LOCAL or CLUSTER only!\n\n\n\n\n"
		exit -1;
	fi;


	printf "${itab2}All CommmandLine Parameters passed!\n"
	printf "${itab1}<== function ValidateInputParameters\n\n"
} #function ValidateInputParameters


function CreateInternalFileNames
{
	local logDir="$1";
	local fnStr="$2";
	local itab1="$3";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";

	printf "${itab1}==> function CreateInternalFileNames\n"

	export TablesList1="${logDir}/${fnStr}_TablesList_Try1_$$.txt"
	export TablesList1B="${logDir}/${fnStr}_TablesList_Try1B_$$.txt"
	export TablesList2="${logDir}/${fnStr}_TablesList_Try2_$$.txt"
	export TablesList2B="${logDir}/${fnStr}_TablesList_Try2B_$$.txt"
	export TablesList3="${logDir}/${fnStr}_TablesList_Try3_$$.txt"
	export TablesSize1="${logDir}/${fnStr}_TablesSize_Try1_$$.txt"
	export TablesSize2="${logDir}/${fnStr}_TablesSize_Try2_$$.txt"
	export TablesSize3="${logDir}/${fnStr}_TablesSize_Try3_$$.txt"

	export DESCRIBE_1_HQL="${logDir}/${fnStr}_DESCRIBE_Try1_$$.hql"
	export DESCRIBE_1_TXT="${logDir}/${fnStr}_DESCRIBE_Try1_$$.txt"

	export DESCRIBE_2_HQL="${logDir}/${fnStr}_DESCRIBE_Try2_$$.hql"
	export DESCRIBE_2_TXT="${logDir}/${fnStr}_DESCRIBE_Try2_$$.txt"
	export  ANALYZE_2_HQL="${logDir}/${fnStr}_ANALYZE_Try2_$$.hql"
	export  ANALYZE_2_TXT="${logDir}/${fnStr}_ANALYZE_Try2_$$.txt"

	export Tables_UNKNOWN_SIZE_1="${logDir}/${fnStr}_Tables_UnknownSize_Try1_$$.txt"
	export Tables_UNKNOWN_SIZE_2="${logDir}/${fnStr}_Tables_UnknownSize_Try2_$$.txt"

	export AllFilesSize_BothTries="${logDir}/${fnStr}_AllFilesSize_BothTries_$$.txt"

	export AllFilesSizeSORTED_BothTries="${logDir}/${fnStr}_AllFilesSizeSORTED_BothTries_$$.txt"
	export DistinctExecutors_BothTries="${logDir}/${fnStr}_DistinctListOfExecutors_BothTries_$$.txt" 
	printf "${itab1}<== function CreateInternalFileNames\n\n"
} #function CreateInternalFileNames



function ClearDirectory
{
        local DirToClear="$1";

        local itab1="$2";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
        printf "${itab1}==> function ClearDirectory;   $DirToClear\n"

	if [[ -d $DirToClear ]]
	then
		rm -r ${DirToClear}/* 2>/dev/null;	#OK if no files there
	else
		printf "\n\n\n\n\nERROR: Directory $DirToClear NOT FOUND\n\n\n\n\n";
		exit -1;
	fi;
        printf "${itab1}<== function ClearDirectory\n\n"
} #function ClearDirectory



function DisplayDirectory
{
        local DirToDisplay="$1";
        local msg1="$2";
        local msg2="$3";
        local itab1="$4";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
        printf "${itab1}==> function DisplayDirectory;  Dir:  $msg1\n"

        printf "${itab2}=======================================================================================================================\n";
        printf "${itab2}Content of Directory $msg1:   $DirToDisplay:\n";
        if [[ ! -z "$msg2" ]]
	then 
		printf "${itab2}$msg2\n"; 
	fi;

	local totalFiles=$(find $DirToDisplay -maxdepth 1 -type f -print |  wc -l | cut -d" " -f1);

	if [[ $totalFiles -eq 0 ]]
	then
		printf "${itab3}(Empty - totalFiles=$totalFiles)\n"
	else
        	ls -ltr $DirToDisplay | grep -v "^total" | sort -k9 | nl | sed "s:^:${itab2}:"  #Sort by fileName (k9) which is in order of creation
	fi;

        printf "${itab2}=======================================================================================================================\n";
        printf "${itab1}<== function DisplayDirectory\n\n"
} #function DisplayDirectory



function MakeDir
{
	local DirToMake="$1";
	local itab1="$2";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	printf "${itab1}==> function MakeDir\n"

	printf "${itab2}Make directory: $DirToMake\n"
	mkdir -p $DirToMake
	local RC=$?
	if [[ $RC -ne 0 ]]
	then
		printf "\n\n\n\n\nERROR: Cannot mkdir $DirToMake;  RC=$RC\n\n\n\n\n";
		printf "${itab1}<== function MakeDir\n"
		exit -1;
	fi;
	printf "${itab1}<== function MakeDir\n\n"
} #function MakeDir



function DisplayGlobalVariables
{
	local itab1="$1";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	printf "${itab1}==> function DisplayGlobalVariables\n"

	printf "

Parameters
	
        G_AUTO_ANALYZE		= $G_AUTO_ANALYZE
        G_CHUNK_SIZE		= $G_CHUNK_SIZE
        G_NUM_EXECUTORS		= $G_NUM_EXECUTORS
        G_MAIL_LIST		= $G_MAIL_LIST
        G_SPARK_MODE		= $G_SPARK_MODE
        G_SPARK_NAME		= $G_SPARK_NAME
        G_ROOT_PROGDIR		= $G_ROOT_PROGDIR
        G_PROJECT		= $G_PROJECT
        G_RESET			= $G_RESET

Variables
	env			= $env
	ENV			= $ENV
	CHUNKSIZE		= $chunkSize bytes
	G_SESSION_DATE		= $G_SESSION_DATE
        G_AUTO_ANALYZE		= $G_AUTO_ANALYZE
        G_CHUNK_SIZE		= $G_CHUNK_SIZE  bytes
        G_NUM_EXECUTORS		= $G_NUM_EXECUTORS
	G_LOG_DIR		= $G_LOG_DIR
	G_CHECKPOINT_DIR	= $G_CHECKPOINT_DIR
	G_CHECKPOINT_ARCHIVE_DIR= $G_CHECKPOINT_ARCHIVE DIR
	G_ARCHIVE_DIR		= $G_ARCHIVE_DIR
	G_TORUN_DIR		= $G_TORUN_DIR
	G_MAIL_LIST		= $G_MAIL_LIST
	G_THIS_SCRIPT_NAME	= $G_THIS_SCRIPT_NAME

Internal FileNames:
	INPUT_LIST		= $G_INPUT_LIST

	TablesList1		= $TablesList1
	TablesList1B		= $TablesList1B
	TablesList2		= $TablesList2
	TablesList3		= $TablesList3

	TablesSize1		= $TablesSize1
	DESCRIBE_1_HQL		= $DESCRIBE_1_HQL
	DESCRIBE_1_TXT		= $DESCRIBE_1_TXT
	Tables_UNKNOWN_SIZE_1	= $Tables_UNKNOWN_SIZE_1

	TablesSize2		= $TablesSize2
	DESCRIBE_2_HQL		= $DESCRIBE_2_HQL
	DESCRIBE_2_TXT		= $DESCRIBE_2_TXT
	ANALYZE_2_HQL		= $ANALYZE_2_HQL
	ANALYZE_2_TXT		= $ANALYZE_2_TXT
	Tables_UNKNOWN_SIZE_2	= $Tables_UNKNOWN_SIZE_2

	AllFilesSize_BothTries		= $AllFilesSize_BothTries
	AllFilesSizeSORTED_BothTries	= $AllFilesSizeSORTED_BothTries
	DistinctExecutors_BothTries	= $DistinctExecutors_BothTries
	\n\n";

	#FAIL_OPTION		= $FAIL_OPTION
	printf "${itab1}<== function DisplayGlobalVariables\n\n"
} #function DisplayGlobalVariables


function RoundUpToNext10
{
	local str="$1";
	local itab1="$2";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";

	#Round up to next decimal value (ex: 2=> 10; 12=> 20; 22=> 30;  119=>120 ...)
	#This allows better grouping to submit to Spark Cluster
	str="0${str}";				#Add 1 leading 0 (in case it is single digit)
	local len=${#str}
	local s1=${str:0:len-1};		#Remove last (rightmost) digit (for roundup)
	local s2=$(printf $s1|sed 's:^0*::');	#Remove leading 0s
	local s3="$((s2+1))0";            	#Add 1, then and trailing 0 (because it was removed above)
	#printf "str=$str; len=$len; s1=$s1; s2=$s2; s3=$s3; numExecutors=$numExecutors\n"
	numExecutors=$s3
	#printf "\t\tAFTER  RoundUpToNext10: numExecutors=$numExecutors\n"
} #function RoundUpToNext10



function CalculateNumExecutor
{
        local fileSize="$1";
	local chunkSize="$2";
	local itab1="$3";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	#Calculate numExecutors based on fileSize:
	numExecutors=$((($fileSize+$chunkSize-1)/$chunkSize))
	#printf "\t\tBEFORE RoundUpToNext10: numExecutors=$numExecutors\n"
} #function CalculateNumExecutor



function GenerateDESCRIBECommands 
{
	local TRY="$1";
	local inputFile="$2";
	local outputFile_HQL="$3";
	local inputFileB="$4";
	local itab1="$5";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	local RC;

	printf "
${itab1}==> function GenerateDESCRIBECommands
${itab2}TRY		= $TRY
${itab2}inputFile	= $inputFile
${itab2}inputFileB	= $inputFileB
${itab2}outputFile_HQL	= $outputFile_HQL\n\n";

	#printf "${itab2}Input file: $inputFile\n";
	#nl $inputFile | sed 's:^:\t\t:'
	#printf "\n\n\n\n";
	
	rm $outputFile_HQL >/dev/null 2>&1
	totalFiles=$(wc -l $inputFile|cut -d" " -f1);
	let N=0
	for table in $(sed -e 's:\t:.:' $inputFile)	#Combine 2 words into 1 word for FOR loop
	do
		N=$((N+1));
		# Check: Valid Input line MUST have  2 words: schema table, separate by TAB
		wordscount=$(echo $table|awk -F"." 'END {print NF}');
		if [[ $wordscount -ne 2 ]]
		then
			# Check: In case input line does not have 2 words (schema table)
			printf "\n\n${itab2}$N/$totalFiles)\t********** ERROR: Bad input line: \"$table\" (wordscount=$wordscount) ==> IGNORE THIS LINE **********\n\n\n"
			continue;  #Skip remaining of loop
		fi;
	
		echo $table | sed 's:\.:\t:' >> $inputFileB;	#Save Only "good" rows
		DESCRIBE_Command=$(echo $table | sed -e 's:\t:.:' -e 's:$:;:' -e 's:^:DESCRIBE EXTENDED :' );

		# Examle Output line: describe extended cca_edge_base_atds_58616_atds_atds_core.aftersales_contract;
		printf "${itab2}$N/$totalFiles)\t$DESCRIBE_Command\n"
		printf "$DESCRIBE_Command\n" >> $outputFile_HQL
	done;
	printf "${itab1}<== function GenerateDESCRIBECommands\n"
} #function GenerateDESCRIBECommands 



function GenerateANALYZECommands 
{
	local TRY="$1";
	local inputFile="$2";
	local outputFile_HQL="$3";
	local itab1="$4";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	local RC;
	local totalFiles;

	printf "
${itab1}==> function GenerateANALYZECommands
${itab2}TRY		= $TRY
${itab2}inputFile	= $inputFile
${itab2}outputFile_HQL	= $outputFile_HQL\n\n";

	rm $outputFile_HQL >/dev/null 2>&1
	totalFiles=$(wc -l $inputFile|cut -d" " -f1);
	maxLen=$(awk '{print length($0)}' $inputFile | sort -n -r | head -1);

	let N=0
	for table in $(sed -e 's:\t:.:' $inputFile)	#Combine 2 words (separate by TAB) into 1 word for FOR loop
	do
		N=$((N+1));
		# Check: Valid Input line MUST have  2 words: schema table, separate by TAB
		wordscount=$(echo $table|awk -F"." 'END {print NF}');
		if [[ $wordscount -ne 2 ]]
		then
			# Check: In case input line does not have 2 words (schema table)
			printf "${itab2}$N/$totalFiles)\tERROR: Bad input line: \"$table\" (wordscount=$wordscount) ==> IGNORE THIS LINE\n"
			continue;
		fi;
		ANALYZE_Command="$(printf "ANALYZE TABLE %-${maxLen}s\tcompute statistics  NOSCAN;\n" $table)"

		# Examle Output line: describe extended cca_edge_base_atds_58616_atds_atds_core.aftersales_contract;
		printf "${itab2}$N/$totalFiles)\t$ANALYZE_Command\n"
		printf "$ANALYZE_Command\n" >> $outputFile_HQL
	done;
	printf "${itab1}<== function GenerateANALYZECommands\n\n";
} #function GenerateANALYZECommands 



function ExecuteHIVECommands 
{
	local TRY="$1";
	local HQL="$2";
	local TXT="$3";
	local FAIL_IF_NONZERO_RC="$4";

	local itab1="$5";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	local RC;

	printf "
${itab1}==> function ExecuteHIVECommands 
${itab2}TRY             = $TRY
${itab2}input script    = $HQL
${itab2}output file     = $TXT
${itab2}FAIL_IF_NONZERO_RC = \"$FAIL_IF_NONZERO_RC\" \n";

	printf "\n${itab2}Input HQL script: $HQL\n";
	#head $HQL | nl | sed 's:^:\t\t:'
	nl $HQL | sed "s:^:${itab2}:"
	printf "\n";

	hive -hiveconf hive.cli.errors.ignore=true  -f $HQL > $TXT 2>&1
		# Use this option to it executes entire script, even if there are errors in middle
	RC=$?
	if [[ $RC -eq 0 ]]
	then
		printf "${itab2}RC=$RC\n"
	else
		if [[ "$FAIL_IF_NONZERO_RC" == "FAIL" ]] || [[ "$FAIL_IF_NONZERO_RC" == "YES" ]]
		then
			printf "\n\n\n\n\nERROR: RC=$RC.  Exiting (FAIL_IF_NONZERO_RC=\"$FAIL_IF_NONZERO_RC\")\n\n\n\n\n"
			printf "${itab1}<== function ExecuteHIVECommands\n\n";
			exit -1;
		else
			printf "\n\n\n\t\t********** WARNING: RC=$RC.  Ignore Error (FAIL_IF_NONZERO_RC=\"$FAIL_IF_NONZERO_RC\") **********\n\n\n"
			printf "${itab2}Read this log file to determine error:  $TXT\n\n\n";
		fi;
	fi;
	printf "${itab1}<== function ExecuteHIVECommands\n\n";
} #function ExecuteHIVECommands 



function ExtractFileSizes
{
	local TRY="$1";
	local inputFile="$2";
	local allTablesDescTXT="$3";
	local chunkSize="$4";
	local output_tablesWithSIZE="$5";
	local inputFile2="$6";
	local output_tablesNOSIZE="$7"
	local default_NUM_EXCUTORS="$8"

	local itab1="$9";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";

	local maxTableLength=$( cut -f1 $inputFile | awk '{print length ($1)}' | sort -n -r|head -1)
	local maxSchemaLength=$(cut -f2 $inputFile | awk '{print length ($1)}' | sort -n -r|head -1)
	local totalLength=$((   maxTableLength + maxSchemaLength + 2))
	local totalTables;

	printf "
${itab1}==> function ExtractFileSizes;
${itab2}\$1 TRY				= $TRY
${itab2}\$2 inputFile			= $inputFile
${itab2}\$3 allTablesDescTXT		= $allTablesDescTXT
${itab2}\$4 chunkSize			= $chunkSize
${itab2}\$5 output_tablesWithSIZE	= $output_tablesWithSIZE
${itab2}\$6 inputFile2			= $inputFile2
${itab2}\$7 output_tablesNOSIZE	= $output_tablesNOSIZE
${itab2}\$8 default_NUM_EXCUTORS	= $default_NUM_EXCUTORS

${itab3}maxTableLength		= $maxTableLength
${itab3}maxSchemaLength		= $maxSchemaLength
${itab3}totalLength		= $totalLength\n\n";

	printf "${itab2}Input file: $inputFile\n";
	#head $inputFile | nl | sed 's:^:\t\t:'
	nl $inputFile | sed 's:^:\t\t:'
	printf "\n\n";

	totalTables=$(wc -l $inputFile|cut -d" " -f1);
	printf "${itab2}Extracting Table Sizes from DESCRIBE commands, and calculating executors based on chunkSize=$chunkSize:\n\n";
	let N=0
	let totalTablesWithSize=0
	let totalTablesNOSize=0
	for line in $(sed -e 's:\t:.:' $inputFile)	#Combine into 1 words for FOR loop
	do
		N=$((N+1));
		wordscount=$(echo $line|awk -F"." 'END {print NF}');
		if [[ $wordscount -ne 2 ]]
		then
			printf "\t\t$N/$totalTables)\tERROR: Bad input line: \"$line\" (wordscount=$wordscount) ==> IGNORE THIS LINE\n"
			continue;
		fi;

		let numExecutors=0
		schema=$(    echo $line   |cut -d"." -f1)
		table=$(     echo $line   |cut -d"." -f2)
		schema_lc=$( echo $schema |tr "[A-Z]" "[a-z]" );	# LowerCase to match hives Describe Extended
		table_lc=$(  echo $table  |tr "[A-Z]" "[a-z]" );

		#Example:   FAILED: SemanticException [Error 10001]: Table not found 
		badcount=$(grep -c "FAILED: SemanticException \[Error 10001\]: Table not found ${schema}.${table}" $allTablesDescTXT)

		printf "${itab3}Table $N/$totalTables)\t%-${totalLength}s:"    "${schema}.${table}"
		if [[ $badcount -gt 0 ]]
		then
			printf "  ********** TABLE NOT FOUND IN HIVE ********** ==> IGNORE\n"
			continue;
		fi;

		# Sometimes the Hive's DESCRIBE EXTENDED splits into 2 or more lines if table has too many columns
		# ==> Use awk to go across multiple lines
		# Note: Case-sensitive of these words
		fileSize=$(	awk  "/tableName:${table_lc}, dbName:${schema_lc}/,/tableType:EXTERNAL_TABLE)/"   $allTablesDescTXT |\
				grep "totalSize=" |\
				sed -e 's:^\(.*totalSize=\)\([0-9]*\).*$:\2:'
			);
		RC=$?
		if [[ $RC -eq 0 ]]
		then
			if [[ "$fileSize" == "" ]] 	#####  || [[ "$fileSize" == "0" ]]
			then	#Sometines there is NO fileSize info for this table from the DESCRIBE command(ie, has not been analyzed yet)

				if [[ -z "$default_NUM_EXCUTORS" ]] || [[ "$default_NUM_EXCUTORS" -eq 0 ]]
				then
					if [[ -f $inputFile2 ]]
					then 
						printf "$schema\t$table\n" >> $inputFile2; 
					fi;
					if [[ -f $output_tablesNOSIZE ]]
					then 
						printf "describe extended $schema.$table;\n" >> $output_tablesNOSIZE
						totalTablesNOSize=$((totalTablesNOSize+1))
					fi;
					printf "  SIZE NOT FOUND (==> need ANALYZE)\n"
				else
					let numExecutors=$default_NUM_EXCUTORS
					printf "  fileSize='$fileSize'\t==> numExecutors=$default_NUM_EXCUTORS (default due to Null Size)\n"
					printf "$numExecutors,${schema}.$table,$fileSize\n" >> $output_tablesWithSIZE
					totalTablesWithSize=$((totalTablesWithSize+1))
				fi;
			else
				CalculateNumExecutor  $fileSize  $chunkSize $itab2
				printf "  fileSize='$fileSize'\t==> numExecutors=\"$numExecutors\";"
				RoundUpToNext10 $numExecutors $itab2;  #If this function not wanted, just comment this 1 line out

        			if   [[ $numExecutors -lt 1     ]] ; then let numExecutors=1;
			        elif [[ $numExecutors -gt 200   ]] ; then let numExecutors=200;
			        fi;

				printf "\tAfter RoundUp=\"$numExecutors\"\n"
				printf "$numExecutors,${schema}.$table,$fileSize\n" >> $output_tablesWithSIZE
			fi;
		else
			printf "Table $table NOT EXIST.  Ignore!\n"
		fi;
	done;
	printf "\n\n";
	printf "${itab2}Output File1=$output_tablesWithSIZE;\t\ttotal=$totalTablesWithSize\n"
	printf "${itab2}Output File2=$output_tablesNOSIZE;\t\ttotal=$totalTablesNOSize\n\n";
	printf "${itab1}<== function ExtractFileSizes\n\n"
} #function ExtractFileSizes



function SortTablesFileSizes
{
	local inputFile="$1"
	local outputFile="$2"
	local itab1="$3";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	printf "
${itab1}==> function SortTablesFileSizes (sort by 1st field: NumExecutors)
${itab2}inputFile  = $inputFile
${itab2}outputFile = $outputFile\n"

	sort -r -t"," -k1 $inputFile > $outputFile

	printf "${itab1}<== function SortTablesFileSizes\n\n"
} # function SortTablesFileSizes



function func_invoke_Pyspark  
{
	local fullExeFile=$1;		# 20180425_115438___010___mdb_list.txt
	local numExecutors=$2;		# 10 or 20 ....
	local sessionDate=$3;		# YYYYMMDD_HHMMSS
	local checkpointDir_OS=$4;	# 
	local exeFileNameOnly=$5;	# ___010___mdb_list.txt
	local sparkName=$6;		# 

	local itab1="$7";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; 
	local itab3="${itab2}\t"; local itab4="${itab3}\t"; local itab5="${itab4}\t"; local itab6="${itab5}\t"; 

	local exeFile=$(    basename $fullExeFile)
	local totalTables=$(wc    -l $fullExeFile | cut -d" " -f1);

	printf "\n\n${itab1}==> function func_invoke_Pyspark
${itab2}fullExeFile	= $fullExeFile
${itab2}checkpointDir	= $checkpointDir
${itab2}numExecutors	= $numExecutors
${itab2}exeFileNameOnly	= $exeFileNameOnly
${itab2}sparkName	= $sparkName

${itab2}exeFile		= $exeFile
${itab2}totalTables	= $totalTables
\n";

			#--name            CHEVELLE	\
			#--master          yarn		\
			#--deploy-mode     cluster	\

			#--master          local[*]	\
			#--deploy-mode     client	\

		/usr/bin/spark-submit			\
			--name            "${sparkName}_${numExecutors}_exec"	\
			--master          yarn		\
			--deploy-mode     cluster	\
			--driver-memory   2g		\
			--executor-memory 2g		\
			--executor-cores  2		\
			--num-executors   $numExecutors	\
			--files /usr/hdp/current/hive-client/conf/hive-site.xml	\
			--conf spark.yarn.appMasterEnv.HADOOP_USER_NAME=$USERID	\
			--conf spark.yarn.maxAppAttempts=1			\
			/data/commonScripts/util/hyper_profile_desc_stats.py $exeFile $G_INPUT_LIST  


			#/home/tqzpftv/CHEVELLEL/hyper_profile_desc_stats.py  $exeFile  $numexe  $sessionDate $checkpointDir $exeFileNameOnly   $totalTables

		local RC=$?
		printf "\n\n${itab1}<== function func_invoke_Pyspark;  RC=$RC\n\n";
		return $RC
}




function Execute
{
	local torunDir="$1";
	local checkpointDir="$2";
	local checkpointArchiveDir="$3";
	local archiveDir="$4";
	local sparkName="$5";

	local itab1="$6";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; 
	local itab3="${itab2}\t"; local itab4="${itab3}\t"; local itab5="${itab4}\t"; local itab6="${itab5}\t"; 
	local n  m  RC num_executors  totalTables  totalSuccess   totalFail   maxLen;

	printf "\n${itab1}==> function Execute
${itab2}torunDir             = $torunDir
${itab2}checkpointDir        = $checkpointDir
${itab2}checkpointArchiveDir = $checkpointArchiveDir
${itab2}archiveDir           = $archiveDir
${itab2}sparkName            = $sparkName
\n";

	local totalFiles=$(ls -1 $torunDir | wc -l|cut -d" " -f1)

	printf "\n${itab2}LOOP thru directory torunDir:\n"
	let n=0;
	for exeFile in $(ls -1 $torunDir | sort -k9)
	do	#{

		n=$((n+1))
		exeFileNameOnly=$(printf "$exeFile"|sed 's:^...............::');	#Remove the 1st 10 chars
		num_executors=$(printf "$exeFile" |sed "s:^.*___\(.*\)___.*$:\1:"); 
		numexe=$(printf $num_executors|sed 's:^0*::'); 	#Remove leading 0s (it was prepended with 0s previously)
		printf "\n${itab3}File $n/$totalFiles) \"$exeFile\";  num_executors=\"$numexe\";  exeFileNameOnly=$exeFileNameOnly\n";

		printf "\n${itab4}A) Remove HDFS file before put new one in, in case it's already there (OK if not exists): \"$exeFile\"\n"
		hadoop fs -rm  /${ENV}/EDW/......./CHEV/HYPER_PROF_RSLT/Input/${exeFile}
		# OK if not exists. 

		printf "\n${itab4}B) Put exeFile into HDFS: $exeFile, for Profile program to use:\n"
		hadoop fs -put ${torunDir}/${exeFile}   /${ENV}/EDW/......./CHEV/HYPER_PROF_RSLT/Input/
		RC=$?
		printf "${itab5}RC=$RC\n"
		if [[ $RC -ne 0 ]]
		then
			printf "\n\n\n\nERROR: Cannot put exeFile into HDFS: ($exeFile); RC=$RC\n\n\n\n";
			exit -1;
		fi;
	
		printf "\n${itab4}C) Submit file, $exeFile, to Spark-Cluster with num_executors=$numexe:\n"

		func_invoke_Pyspark      "${torunDir}/${exeFile}"   $numexe  "$G_SESSION_DATE"  $checkpointDir  "$exeFileNameOnly"   "$sparkName"  "$itab4"
		RC=$?
		if [[ $RC -ne 0 ]]
		then
			printf "\n\n\n\n\nERROR: Spark Job failed; RC=$RC\n\n\n\n\n";
			exit -1;
		else
			printf "${itab5}RC=$RC; Spark Job completed successfully\n";
		fi;
	

		printf "${itab5}All $totalTables tables for this exeFile have been profiled successfully.\n\n";

		printf "${itab4}E) Move Input HDFS file, $exeFile, from HDFS INPUT Dir to HDFS Archive Dir:\n";
		MoveHDFSFileToHDFSArchive  $exeFile  "${itab5}"


		#Move input file out of TORUN dir so it won't be executed again.  Put in Archive just for easy viewing
		printf "\n${itab4}G) Move OS file $exeFile from torunDir ($torunDir) to archiveDir ($archiveDir)\n";
		mv ${torunDir}/${exeFile} $archiveDir

		RC=$?
		if [[ $RC -ne 0 ]]
		then
			printf "\n\n\n\n\nERROR: Cannot remove exeFile;  RC=$RC.\n\n\n\n\n";
			exit -1;
		fi;

		printf "\n";

	done;	#}
	printf "${itab2}END LOOP;"

} #function Execute



function MoveHDFSFileToHDFSArchive
{
	local inputFile="$1";
	local itab1="$2";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; local itab3="${itab2}\t";
	local RC;


	printf "${itab1}==> function MoveHDFSFileToHDFSArchive\n";

	local x=$(date +%Y%m%d_%H%M%S);
	local bn=$(basename $inputFile .txt);
	local archiveName="${bn}_${x}.txt";	# Add date to filename
	local hdfsInputFile="/${ENV}/EDW/......./CHEV/HYPER_PROF_RSLT/Input/${inputFile}"
	local hdfsArchiveFile="/${ENV}/EDW/......./CHEV/HYPER_PROF_RSLT/Archive/${archiveName}"

	printf "${itab2}Move   $hdfsInputFile   to   $hdfsArchiveFile:\n"

	#hadoop fs -mv	/${ENV}/EDW/......./CHEV/HYPER_PROF_RSLT/Input/${inputFile}	\
	#		/${ENV}/EDW/......./CHEV/HYPER_PROF_RSLT/Archive/${archiveName}

	hadoop fs -mv	$hdfsInputFile  $hdfsArchiveFile
	RC=$?
	printf "${itab2}RC=$RC\n"
	if [[ $RC -ne 0 ]]
	then
		printf "\n\n\nERROR: Cannot move input OS file ($bn) to Archive ($archiveName); RC=$RC\n\n\n"
		exit -1;
	fi;
	printf "${itab1}<== function MoveHDFSFileToHDFSArchive\n";
} #function MoveHDFSFileToHDFSArchive



function GenerateTORUNScripts
{
	local inputFile="$1";
	local distinctExecutors="$2";
	local logDir="$3";
	local fnStr="$4";
	local destDir="$5"
	local sessionDate="$6"

	local itab1="$7";   if [[ -z "$itab1" ]]; then itab1=""; fi; local itab2="${itab1}\t"; 
	local itab3="${itab2}\t"; local itab4="${itab3}\t";
	local tablesWithSameExecutors="${logDir}/${fnStr}_tablesWithSameExecutors_$$.txt";
	local n  num_executorsPADDED outFile;

	printf "\n${itab1}==> function GenerateTORUNScripts
${itab2}inputFile               = $inputFile
${itab2}distinctExecutors       = $distinctExecutors
${itab2}tablesWithSameExecutors = $tablesWithSameExecutors
${itab2}logDir  = $logDir
${itab2}fnStr   = $fnStr
${itab2}destDir = $destDir
\n";

	let n=0;
	for num_executors in $(cat $distinctExecutors)	#{
	do
		n=$((n+1))
		grep "^${num_executors},"  $inputFile  | sort > $tablesWithSameExecutors
		printf "\n${itab2}Group #$n) Num-Executors = $num_executors\n"
		printf "\n${itab3}A) List of tables for this Group:\n";		#Display for debug
		sed -e "s:^:${itab4}:" $tablesWithSameExecutors
 
		num_executors_PADDED=$(printf "%03d" $num_executors); # Always 3 digits, leading 0, for numeric Sorting next
		outFile="${destDir}/${sessionDate}___${num_executors_PADDED}___${G_INPUT_LIST}";
			# 2 fields: 1) SessionDate:   to identify a group
			#           2) num_executors:

		printf "\n${itab3}B) ReFormat output file: \"SchemaName<tab>TableName\" ($outFile)\n"
		cut -d"," -f2 $tablesWithSameExecutors | sed "s:\.:\t:" > $outFile;	#
		sed -e "s:^:${itab4}:" $outFile;	#Display for debug
	done;	#} 
	printf "\n\n";
	DisplayDirectory $destDir    "destDir"     ""    "${itab2}";
	printf "\n\n";
	printf "${itab3}There are $n scripts to submit to Spark\n\n\n";
	printf "${itab1}<== function GenerateTORUNScripts\n\n";
} #function GenerateTORUNScripts


#------------------------------------------------------------------------------------------------------------------------
#-------------------------------------------- MAIN MAIN MAIN MAIN MAIN MAIN MAIN MAIN -----------------------------------
#------------------------------------------------------------------------------------------------------------------------

#Initialize:
clear;

export G_ROOT_PROGDIR="/data/commonScripts/util/CHEVELLE/STAT";	#Default
#export G_ROOT_PROGDIR="/tmp/CHEVELLE/PROFILE";	#Default

export G_JOB_STATUS="NOT_START_YET";	
export G_JOB_STEP_COMPLETE="0";
export G_SESSION_DATE=$(date +%Y%m%d_%H%M%S)
export G_THIS_SCRIPT_NAME="$(basename $0)";
export G_CHUNK_SIZE="100000000";
export G_CHUNK_SIZE_DEFAULT="100000000";
export G_NUM_EXECUTORS="40";
export G_NUM_EXECUTORS_DEFAULT="40";
export G_AUTO_ANALYZE="NO";
export env="";
export ENV="";
export G_RESET="NO";			# for Remove all old generated input files
export G_FNSTR="CP";			#FNST=FileNameString;  CP=Chevelle Profiling;  Used to make filenames
export G_MAIL_LIST="";		
export G_INPUT_LIST="";
export G_INPUT_LIST="";
export G_DAYS_TO_REMOVE="30";

export G_SPARK_MODE="LOCAL";	#SparkMode: Cluster or Local.  Default=LOCAL


# Set these as defaults for now:
export G_PROJECT="CHEVELLE_STAT";	# Default GENERAL LOGDIR (if user did not specify at command line)
export G_SPARK_NAME="CHEVELLE_STAT";	# SparkName: For YARN UI
export G_AUTO_ANALYZE="YES";		# Deafult=NO

trap "Func_Trap_INT"    INT 

printf "\n\n\n"
while getopts "ahRc:e:M:N:m:L:P:"  OPTION
do
        case $OPTION in
        a)      G_AUTO_ANALYZE="YES";;		#                                                   Deafult=NO
        c)      G_CHUNK_SIZE="$OPTARG";;	#Each Chunk (bytes) is assigned as 1 Executor.      Default=100,000,000 bytes
        e)      G_NUM_EXECUTORS="$OPTARG";;	#This is numExecutors to use if FileSize is empty.  Default=40
        m)      G_MAIL_LIST="$OPTARG";;		#Comma-separated list of email address.             Default=nobody
        M)      G_SPARK_MODE="$OPTARG";;	#SparkMode: Cluster or Local.                       Default=LOCAL
        N)      G_SPARK_NAME="$OPTARG";;	#SparkName: for submitting JobName to Spark.        Default=CHEVELLE
        L)      G_ROOT_PROGDIR="$OPTARG";;      #                                                   Default=
        P)      G_PROJECT="$OPTARG";;		#                                                   Default=DEFAULT_PROJECT       # TBD
        R)      G_RESET="YES";;			#                                                   Deafult=NO
        h)      Usage; exit;			#
        esac;
done;
shift $((OPTIND - 1));

if [[ -z "$1" ]]
then
	printf "\n\n\n\n\nERROR:  Missing parameter 1!  Must enter a filename to retrieve from HDFS.\n\n\n\n\n"
	exit -1;
else
	G_INPUT_LIST="tbl_list${1}.txt";	# This is what the user specifies at command line
fi;

ValidateInputParameters	$G_INPUT_LIST  "" ;
GetENVvariable  

export G_ROOT_DIR="${G_ROOT_PROGDIR}/${G_PROJECT}/${G_INPUT_LIST}";
		#Difference: G_ROOT_DIR   and G_ROOT_PROG_DIR
export G_CHECKPOINT_DIR="${G_ROOT_DIR}/CHECKPOINT";  
export G_CHECKPOINT_ARCHIVE_DIR="${G_ROOT_DIR}/CHECKPOINT/ARCHIVE";   
export G_TORUN_DIR="${G_ROOT_DIR}/TORUN";
export G_ARCHIVE_DIR="${G_ROOT_DIR}/ARCHIVE";
export G_LOG_DIR="${G_ROOT_DIR}/LOGS/${G_SESSION_DATE}";

export chunkSize=$G_CHUNK_SIZE;	#Minumum: 100M

# These dirs are Linux OS dirs (Not yet at Spark Cluster)
MakeDir                 $G_LOG_DIR;
MakeDir                 $G_CHECKPOINT_DIR;
MakeDir                 $G_CHECKPOINT_ARCHIVE_DIR;
MakeDir                 $G_TORUN_DIR;
MakeDir                 $G_ARCHIVE_DIR;

CreateInternalFileNames $G_LOG_DIR  $G_FNSTR;
DisplayGlobalVariables;  


rm -f ${G_TORUN_DIR}/*    



if [[ "$G_RESET" == "YES" ]]
then
	printf "\n\nUser requests RESET ==> Clear out directory TORUN: $G_TORUN_DIR\n"
	printf "\nContent of Directory TORUN BEFORE Reset:\n"
	DisplayDirectory $G_TORUN_DIR   "===== ToRunDir"   "===== (BEFORE ClearDirectory)"  "\t"
	ClearDirectory   $G_TORUN_DIR   "\t"

else

	printf "\n\nCHECKING: Scripts from previous failed runs:\n" 
	DisplayDirectory $G_TORUN_DIR    "ToRunDir"    ""  "\t"
fi;
printf  "\n\n";


# Get file from HDFS (containing list of schema.tables) to calculate num_executors for each table
printf "\n\nSTEP 1: Get list, $G_INPUT_LIST,  from HDFS into this local file:  ${TablesList1}\n"
hadoop fs -get /${ENV}/EDW/......./CHEV/HYPER_PROF_RSLT/Input/${G_INPUT_LIST}    $TablesList1
RC=$?
printf "\tRC=$RC\n"
if [[ $RC -ne 0 ]]
then	printf "\n\n\n\n\nERROR:  Cannot get Input File (list of tables to profile) from Hadoop: $G_INPUT_LIST;  RC=$RC\n\n\n\n\n";
	exit 0;
fi;


if [[ -s $TablesList1 ]]
then
	
	printf "\nPerforming  dos2unix on input file\n"
	dos2unix $TablesList1
else
	printf "\n\n\n\n\nERROR: Input file is empty $TablesList1\n\n\n\n\n"
	exit -1;
fi;

chmod 755 $TablesList1



G_JOB_STEP_COMPLETE="STEP1";	


# Create empty files are the function "ExtractFileSizes" detects the existence of these files to write to them
>$Tables_UNKNOWN_SIZE_1; chmod 755 $Tables_UNKNOWN_SIZE_1;
>$Tables_UNKNOWN_SIZE_2; chmod 755 $Tables_UNKNOWN_SIZE_2; 
>$TablesList1B;          chmod 755 $TablesList1B;
>$TablesList2;           chmod 755 $TablesList2;
>$TablesList3;           chmod 755 $TablesList3;

#### RemoveOldFiles $G_DAYS_TO_REMOVE  $G_ROOT_DIR


printf "\n\n\n\n";
printf "==========================================================================================================\n";
printf "================================================ FIRST TRY ===============================================\n";
printf "==========================================================================================================\n\n\n";
TRY=FIRST
>$TablesSize1; chmod 755 $TablesSize1;

printf "\n\nSTEP 2: Generate Hive DESCRIBE commands for input tables:\n";
GenerateDESCRIBECommands  $TRY        $TablesList1       $DESCRIBE_1_HQL   $TablesList1B  "\t";
G_JOB_STEP_COMPLETE="STEP2";	


printf "\n\nSTEP 3: Execute Hive DESCRIBE commands:\n";
ExecuteHIVECommands       $TRY        $DESCRIBE_1_HQL  $DESCRIBE_1_TXT  "CONTINUE"   "\t";
G_JOB_STEP_COMPLETE="STEP3";	


printf "\n\nSTEP 4: Extract Table Filesizes:\n";
ExtractFileSizes          $TRY                    $TablesList1B     $DESCRIBE_1_TXT	\
                          $chunkSize              $TablesSize1      $TablesList2	\
                          $Tables_UNKNOWN_SIZE_1  0                 "\t" 
G_JOB_STEP_COMPLETE="STEP4";	

	# In this FIST TRY we pass in 0 , meaning DO NOT apply default NUM_EXECUTORS
	# for tables that DONOT have fileSize (ie, has not been analyzed yet)
	# In the SECOND TRY we will pass in G_NUM_EXECUTORS to allow it to use DEFAULT NUM_EXECUTORS


printf "\n\n\n\t=======================================================================================================\n";
printf "\tFIRST Try Results:

\tFile #1) These Tables have FileSizes (to determined num_executors)\n";
if [[ -f $TablesSize1 ]]
then	
	nl  $TablesSize1  | sed 's:^:\t\t:' 
	cat $TablesSize1  >> $AllFilesSize_BothTries
else
	printf "\n\n\n\n\nERROR: Cannot find file:  TablesSize1: $TablesSize1\n\n\n\n\n"
	exit -1;
fi;

total_NOSIZE=$(wc -l $Tables_UNKNOWN_SIZE_1  | cut -d" " -f1)
printf "\n\n\tFile #2) These Tables DO NOT have FileSizes.  (Must Describ/Analyze these tables next)\n";
if [[ -s $Tables_UNKNOWN_SIZE_1 ]]
then
	nl $Tables_UNKNOWN_SIZE_1  | sed 's:^:\t\t:' 
	printf "\n\t=======================================================================================================\n\n\n\n";
else
	printf "\t\t***** EMPTY *****\n";
	printf "\t\tThis is good as ALL tables have FileSizes to determine num-executors!\n";
	printf "\n\n\t\t==> Skip STEP 5,6,7,8,9: because all valid tables have sizes.\n\n";
	printf "\t=======================================================================================================\n\n\n\n";

	> $TablesSize2  ;  #Create empty file
fi;


if [[ $total_NOSIZE -gt 0 ]]
then
	printf "==========================================================================================================\n";
	printf "========================= SECOND TRY: Analyze remaining tables with UNKNOWN_SIZE =========================\n";
	printf "==========================================================================================================\n\n\n";
	TRY=SECOND
	> $TablesSize2 
	if [[ -s $Tables_UNKNOWN_SIZE_1 ]]
	then
		#cp $Tables_UNKNOWN_SIZE_1   $DESCRIBE_2_HQL 
		printf "\n\nSTEP 5: Generate ANALYZE commands of above file: Tables_UNKNOWN_SIZE_1:\n";
		GenerateANALYZECommands   $TRY  $TablesList2      $ANALYZE_2_HQL     "\t"
		G_JOB_STEP_COMPLETE="STEP5";	
	
		printf "\n\nSTEP 6: Execute ANALYZE commands:\n";
		ExecuteHIVECommands       $TRY  $ANALYZE_2_HQL   $ANALYZE_2_TXT   "CONTINUE"  "\t"
		G_JOB_STEP_COMPLETE="STEP6";	
	
		printf "\n\nSTEP 7: Generate DESCRIBE commands:\n";
		GenerateDESCRIBECommands  $TRY  $TablesList2     $DESCRIBE_2_HQL   $TablesList2B "\t"
		G_JOB_STEP_COMPLETE="STEP7";	
	
		printf "\n\nSTEP 8: DESCRIBE tables_UNKNOWN_SIZE_1:\n";
		ExecuteHIVECommands       $TRY  $DESCRIBE_2_HQL  $DESCRIBE_2_TXT  "CONTINUE"   "\t"
		G_JOB_STEP_COMPLETE="STEP8";	
	
		printf "\n\nSTEP 9: Extract Table Filesizes:\n";
		ExtractFileSizes	$TRY                     $TablesList2B      $DESCRIBE_2_TXT	\
					$chunkSize               $TablesSize2       $TablesList3		\
					$Tables_UNKNOWN_SIZE_2   $G_NUM_EXECUTORS   "\t"
		G_JOB_STEP_COMPLETE="STEP9";	
	
				# NOTE: In this 2nd TRY, we pass in  G_NUM_EXECUTORS (non-zero) so that 
				#       it will use it to assign default NUM_EXECUTORS in case it's still
				#	could not find FileSize (ie, fail to analyze command for whatever reason)
				#	In the 1st TRY above we passed in 0.
		printf "\n\n\n\t=======================================================================================================\n";
		printf "\tSECOND Try Results:\n
	\tFile 1) \"TablesSize2\":  Tables that have FileSize (to determined num_executors)\n";
		if [[ -f $TablesSize2 ]]
		then 
			nl  $TablesSize2  | sed 's:^:\t\t:' 
			cat $TablesSize2  >> $AllFilesSize_BothTries
		else 
			printf "\n\nWARNING: Cannot find output file:  TablesSize2: $TablesSize2\n\n"
		fi;
	
		printf "\n\n\tFile 2) \"Tables_UNKNOWN_SIZE_2\":   Tables that DO NOT have FileSize.\n";
		printf "\t\t\t(Should be EMPTY as we have applied default executors.  Error if not EMPTY)\n";
		if [[ -s $Tables_UNKNOWN_SIZE_2 ]]
		then 
			nl $Tables_UNKNOWN_SIZE_2  | sed 's:^:\t\t:' 
			printf "\n\n\n\n\nERROR: This file should be EMPTY as we have applied default executors\n\n\n\n\n"
		else
			printf "\t\t***** EMPTY *****\n";
		fi;
		printf "\t=======================================================================================================\n\n\n\n";
	fi;
	
fi;

printf "\nSTEP 10: Combine outputs from FIRST try and SECOND try:\n";
cat $TablesSize1 $TablesSize2  > $AllFilesSize_BothTries  
G_JOB_STEP_COMPLETE="STEP10";	


printf "\n\nSTEP 11: Sort by Size:\n";
SortTablesFileSizes     $AllFilesSize_BothTries  $AllFilesSizeSORTED_BothTries "\t"
G_JOB_STEP_COMPLETE="STEP11";	

printf "\n\nSTEP 12: Generate list of DISTINCT Executors:\n";
cut -d"," -f1     $AllFilesSizeSORTED_BothTries | sort -u >$DistinctExecutors_BothTries
G_JOB_STEP_COMPLETE="STEP12";	


printf "\n\nSTEP 13: VERIFY:  (These info below are for this run only.  There could be old files from CHECKPOINT_DIR)\n";
printf "\n\n\tA) TRY 1: Tables with FileSize: ($TablesSize1)\n";  nl $TablesSize1            |sed 's:^:	:';
printf "\n\n\tB) TRY 2: Tables AFTER Analyze: ($TablesSize2)\n";  
		if [[ -s $TablesSize2 ]]
		then
			nl $TablesSize2            |sed 's:^:	:';
		else
			printf "\t\t(Empty)\n\n";
		fi;

printf "\n\n\tC) BOTH:  $AllFilesSize_BothTries\n";             nl $AllFilesSize_BothTries |sed 's:^:	:';
totalDistinct=$(wc -l $DistinctExecutors_BothTries|cut -d" " -f1)
printf "\n\n\t4) DISTINCT EXECUTORS: $totalDistinct (File $DistinctExecutors_BothTries)\n";
let n=0;
for numExe in $(cat $DistinctExecutors_BothTries)
do
	n=$((n+1))
	total=$(grep "^${numExe}," $AllFilesSizeSORTED_BothTries |wc -l);
	printf "\t\t$n)\tExecutor = $numExe\t\thas $xxx $total\ttables\n"
done;
printf "\n\n";
G_JOB_STEP_COMPLETE="STEP13";	


printf "\n\nSTEP 14: Generate Scripts per Session And Executors:\n";
# This to allow checkpointing/Restartability
GenerateTORUNScripts	$AllFilesSizeSORTED_BothTries	\
			$DistinctExecutors_BothTries	\
			$G_LOG_DIR			\
			$G_FNSTR 			\
			$G_TORUN_DIR			\
			$G_SESSION_DATE		"\t";
G_JOB_STEP_COMPLETE="STEP14";	

printf "\n\nSTEP 15: Submit to SparkCluster to profile tables:\n";
Execute  $G_TORUN_DIR    $G_CHECKPOINT_DIR    $G_CHECKPOINT_ARCHIVE_DIR    $G_ARCHIVE_DIR   $G_SPARK_NAME    "\t";
G_JOB_STEP_COMPLETE="STEP15";	


printf "\n\nSTEP 16: Move Original input HDFS file (from command line), $G_INPUT_LIST, from HDFS INPUT Dir to HDFS Archive Dir:\n";
MoveHDFSFileToHDFSArchive $G_INPUT_LIST "\t"
	# The original HDFS input file might be split into mulitple input files (based on NumExecutors)
	# Any of these new input files might Succeed or Fail
	# So, for simplicity, just move this original HDFS input file to HDFS Arhive 
	# User must always put this input file back
G_JOB_STEP_COMPLETE="STEP16";	

#       printf "\n\nSTEP 17: Remove files older than $G_DAYS_TO_REMOVE:\n";
#       RemoveOldFiles $G_DAYS_TO_REMOVE  $G_ROOT_DIR
#       G_JOB_STEP_COMPLETE="STEP17";

G_JOB_STATUS="SUCCESS";	
printf "\n\nJOB Completed   $G_JOB_STATUS\n\n"


