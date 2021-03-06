# CPU Monitoring System

Consider a monitoring system, which monitors 1000 servers. Each server has 2 CPUs. Each

server generates a log for CPU usage every minute.

The format is like this:

timestamp IP cpu_id usage

1414689783 192.168.1.10 0 87

1414689783 192.168.1.10 1 90

1414689783 192.168.1.11 1 93

(1) Please write a simulator to generate the logs for one day, say 2014-10-31, just use

random numbers between 0% to 100% as CPU usage. The generator should write data

to files in a directory. The timestamp is Unix time.

(2) Please create a command line tool which takes a directory of data files as a parameter

and lets you query CPU usage for a specific CPU in a given time period. It is an

interactive command line tool which read a user’s commands from stdin.

The tool should support two commands. One command will print results to stdout. Its

syntax is QUERY IP cpu_id time_start time_end. Time_start and time_end should be

specified in the format YYYY-MM-DD HH:MM where YYYY is a four digit year, MM is a

two digit month (i.e., 01 to 12), DD is the day of the month (i.e., 01 to 31), HH is the hour

of the day, and MM is the minute of an hour. The second command to support is EXIT.

It will exit the tool.

The tool may take several minutes to initialize, but the query result should be returned

within 1 second.

E.g.

To run the generator:

./generate.sh DATA_PATH

To run the interactive query tool:

./query.sh DATA_PATH

>query 192.168.73.55 1 2014-10-31 00:00 2014-10-31 00:05

CPU1 usage on 192.168.1.10:

(2014-10-31 00:00, 90%), (2014-10-31 00:01, 89%), (2014-10-31 00:02, 87%), (2014-10-31

00:03, 94%) (2014-10-31 00:04, 88%)

>QUERY 192.168.1.12 0 2014-10-31 00:00 2014-10-31 00:05

CPU0 usage on 192.168.1.12:

(2014-10-31 00:00, 90%), (2014-10-31 00:01, 89%), (2014-10-31 00:02, 87%), (2014-10-31

00:03, 94%), (2014-10-31 00:04, 88%)

>EXIT


# Executing

- Compiling the source code
1. Open cmd
2. Run complieSource.cmd file

- Executing the logging simulator
1. Run the runLogger.cmd
2. Provide the desired output file path to the runLogger.cmd script.
3. Syntax: runLogger.cmd output.txt

- Executing the query tool
1. Run the runQueryTool.cmd
2. Provide the desired input log file path to the runQueryTool.cmd script.
3. Syntax: runQueryTool.cmd output.txt


# System Config

- Java 8
- Windows 11