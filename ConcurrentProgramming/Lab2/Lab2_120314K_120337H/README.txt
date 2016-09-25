1. To compile the C source codes

    A. Serial Linkedlist
        gcc -g -Wall -o linked_list_serial linked_list_serial.c

    B. Linked list with Mutex
        gcc -g -Wall -o linked_list_mutex linked_list_mutex.c -lm -lpthread

    C. Linked list with read write lock
        gcc -g -Wall -o linked_list_rw linked_list_rw.c -lm -lpthread


2. To run the compiled programs

    A. Serial Linkedlist
        ./linked_list_serial <no-of-elements> <no-of-operations> <fraction-of-member> <fraction-of-insert> <fraction-of-delete>

        Example:
        ./linked_list_serial 1000 10000 0.99 0.005 0.005

    B. Linked list with Mutex
        ./linked_list_mutex <no-of-elements> <no-of-operations> <fraction-of-member> <fraction-of-insert> <fraction-of-delete> <no-of-threads>

        Example:
        ./linked_list_mutex 1000 10000 0.99 0.005 0.005 2

    C. Linked list with read write lock
        ./linked_list_rw <no-of-elements> <no-of-operations> <fraction-of-member> <fraction-of-insert> <fraction-of-delete> <no-of-threads>

        Example:
        ./linked_list_rw 1000 10000 0.99 0.005 0.005

3. To get the average, standard deviation and no of samples, execute the Python script
    python execute.py
