import subprocess
import statistics
import math

# Number of samples used
NO_OF_SAMPLES = 100

# Compile all the source codes
def compileAll():
    subprocess.call(['gcc', '-g', '-Wall', '-o', 'linked_list_serial', 'linked_list_serial.c'])
    subprocess.call(['gcc', '-g', '-Wall', '-o', 'linked_list_mutex', 'linked_list_mutex.c', '-lm', '-lpthread'])
    subprocess.call(['gcc', '-g', '-Wall', '-o', 'linked_list_rw', 'linked_list_rw.c', '-lm', '-lpthread'])

# Execute a given process and calculate the average and standard deviation
def execute(command):
    elapsedTimes = []
    for i in range(NO_OF_SAMPLES):
        time = subprocess.Popen(command, stdout=subprocess.PIPE).communicate()[0]
        elapsedTimes.append(float(time))

    average = statistics.mean(elapsedTimes)
    standardDeviation = statistics.stdev(elapsedTimes)
    samples = math.ceil(math.pow(((100 * 1.96 * standardDeviation) / (5 * average)), 2))
    print('Average: ' + str(average))
    print('Std.Dev: ' + str(standardDeviation))
    print('Samples: ' + str(samples))

# Execute a list of commands
def executeCommands(commands):
    for i in range(len(commands)):
        if(i is 0):
            print("No of Threads: 1")
        else:
            print("No of Threads: " + str(i * 2))
        execute(commands[i])
        print("")


# Collection of commands to be executed
serial = [['./linked_list_serial', '1000', '10000', '0.99', '0.005', '0.005'], ['./linked_list_serial', '1000', '10000', '0.9', '0.05', '0.05'], ['./linked_list_serial', '1000', '10000', '0.5', '0.25', '0.25']]
mutex1 = [['./linked_list_mutex', '1000', '10000', '0.99', '0.005', '0.005', '1'], ['./linked_list_mutex', '1000', '10000', '0.99', '0.005', '0.005', '2'], ['./linked_list_mutex', '1000', '10000', '0.99', '0.005', '0.005', '4']]
mutex2 = [['./linked_list_mutex', '1000', '10000', '0.9', '0.05', '0.05', '1'], ['./linked_list_mutex', '1000', '10000', '0.9', '0.05', '0.05', '2'], ['./linked_list_mutex', '1000', '10000', '0.9', '0.05', '0.05', '4']]
mutex3 = [['./linked_list_mutex', '1000', '10000', '0.5', '0.25', '0.25', '1'], ['./linked_list_mutex', '1000', '10000', '0.5', '0.25', '0.25', '2'], ['./linked_list_mutex', '1000', '10000', '0.5', '0.25', '0.25', '4']]
rw1 = [['./linked_list_rw', '1000', '10000', '0.99', '0.005', '0.005', '1'], ['./linked_list_rw', '1000', '10000', '0.99', '0.005', '0.005', '2'], ['./linked_list_rw', '1000', '10000', '0.99', '0.005', '0.005', '4']]
rw2 = [['./linked_list_rw', '1000', '10000', '0.9', '0.05', '0.05', '1'], ['./linked_list_rw', '1000', '10000', '0.9', '0.05', '0.05', '2'], ['./linked_list_rw', '1000', '10000', '0.9', '0.05', '0.05', '4']]
rw3 = [['./linked_list_rw', '1000', '10000', '0.5', '0.25', '0.25', '1'], ['./linked_list_rw', '1000', '10000', '0.5', '0.25', '0.25', '2'], ['./linked_list_rw', '1000', '10000', '0.5', '0.25', '0.25', '4']]

mutex = [mutex1, mutex2, mutex3]
rw = [rw1, rw2, rw3]

# Compile all the files
compileAll()

# Execute and print the output
for i in range(3):
    print('------------ CASE: ' + str(i + 1) + ' ------------')
    print('Serial:')
    print('```````')
    execute(serial[i])
    print('')
    print('Mutex:')
    print('``````')
    executeCommands(mutex[i])
    print('')
    print('ReadWrite:')
    print('``````````')
    executeCommands(rw[i])
