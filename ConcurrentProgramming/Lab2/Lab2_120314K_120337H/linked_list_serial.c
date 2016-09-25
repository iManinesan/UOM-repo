/* File:     linked_list_serial.c
 *
 * Purpose:  Implement a sorted linked list of ints with ops insert,
 *           member, delete.
 * Compile:  gcc -g -Wall -o linked_list_serial linked_list_serial.c
 * Run:      ./linked_list_serial <no-of-elements> <no-of-operations> <fraction-of-member> <fraction-of-insert> <fraction-of-delete>
 */
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <sys/time.h>

// Maximum value of random numbers
#define MAX_VALUE 65536

// Struct for linked list node
struct list_node_s {
    int    data;
    struct list_node_s* next;
};

// Global Variables
struct list_node_s* head_p = NULL;
int noOfOperations;
int noOfMember;
int noOfInsert;
int noOfDelete;

// Declaration of Functions
int  Insert(int value);
int  Member(int value);
int  Delete(int value);
void doOperations();
void initialize(int);
int generateRandom(void);
long getCurrentTime(void);


/*-----------------------------------------------------------------*/
/*
 * Main method to execute the program.
 */
int main(int arc, char *argv[]) {
    // Validate the arguments
    if (arc != 6) {
        printf("Invalid number of arguments %d\n", arc);
        return -1;
    }
    // Variables
    long start, finish, elapsed;

    // Collect and interpret the arguments
    int noOfVariables = atoi(argv[1]);
    noOfOperations = atoi(argv[2]);
    noOfMember = strtod(argv[3], NULL) * noOfOperations;
    noOfInsert = strtod(argv[4], NULL) * noOfOperations;
    noOfDelete = strtod(argv[5], NULL) * noOfOperations;

    // Initialize the linked list
    initialize(noOfVariables);

    // Get the starting time
    start = getCurrentTime();

    // Do the operations
    doOperations();

    // Get the ending time
    finish = getCurrentTime();

    // Calculate the elapsed time
    elapsed = finish - start;

    // Print the time to stdout
    printf("%ld", elapsed);
    return 0;
}

/*-----------------------------------------------------------------*/
/* Function:   Get Current Time
 * Purpose:    Generate the current time in milliseconds.
 */ 
long getCurrentTime() {
    struct timeval te;
    gettimeofday(&te, NULL); // get current time
    long milliseconds = te.tv_sec * 1000LL + te.tv_usec / 1000; // caculate milliseconds
    return milliseconds;
}


/*-----------------------------------------------------------------*/
/* Function:   Generate Random
 * Purpose:    Generate random number within the MAXVALUE range.
 */ 

int generateRandom() {
    int value = rand() % MAX_VALUE;
    return value;
}

/*-----------------------------------------------------------------*/
/* Function:    Initialize
 * Purpose:     Initialize the array using random numbers.
 * In arg:      value, the number of variables(n)
 */
void initialize(int noOfVariables) {
    srand (time(NULL));
    int Inserted = 0;
    int i;
    for (i = 0; i < noOfVariables; i++) {
        Inserted = Insert(generateRandom());
        if (!Inserted) {
            i--;
        }
    }
}


/*-----------------------------------------------------------------*/
/* Function:    Do Operations
 * Purpose:     Call the operation functions 
 */
void doOperations() {
    long i;
    for (i = 0; i < noOfOperations; i++) {
        if (i < noOfInsert) {
            int value = generateRandom();
            Insert(value);
        } else if (i < noOfInsert + noOfDelete) {
            int value = generateRandom();
            Delete(value);
        } else {
            int value = generateRandom();
            Member(value);
        }
    }
}

/*-----------------------------------------------------------------*/
/* Function:   Insert
 * Purpose:    Insert value in correct numerical location 
 *             into list.  If value is already in the list, 
 *             print a message and return 
 * In arg:     value, the value to be inserted            
 * In/out arg: head_pp, a pointer to the head of the list pointer
 * Return val: 1 if value was inserted, 0 otherwise
 */       
int Insert(int value) {
    struct list_node_s* curr_p = head_p;
    struct list_node_s* pred_p = NULL;
    struct list_node_s* temp_p;

    while (curr_p != NULL && curr_p->data < value) {
        pred_p = curr_p;
        curr_p = curr_p->next;
    }

    if (curr_p == NULL || curr_p->data > value) {
        temp_p = malloc(sizeof(struct list_node_s));
        temp_p->data = value;
        temp_p->next = curr_p;
        if (pred_p == NULL)
            head_p = temp_p;
        else
            pred_p->next = temp_p;
        return 1;
    } else {
        return 0;
    }
}

/*-----------------------------------------------------------------*/
/* Function:    Member
 * Purpose:     Search list for value
 * In args:     value, the value to be searched for
 *              head_p, pointer to the head of the list  
 * Return val:  true, if value is in the list, false otherwise
 */
int  Member(int value) {
    struct list_node_s* curr_p;

    curr_p = head_p;
    while (curr_p != NULL && curr_p->data < value)
        curr_p = curr_p->next;

    if (curr_p == NULL || curr_p->data > value) {
        return 0;
    } else {
        return 1;
    }
}

/*-----------------------------------------------------------------*/
/* Function:    Delete
 * Purpose:     If value is in the list, delete it.  Otherwise, just
 *              return.
 * In arg:      value, the value to be deleted
 * In/out arg:  head_pp, pointer to the head of the list pointer
 * Return val:  1 if value is deleted, 0 otherwise
 */
int Delete(int value) {
    struct list_node_s* curr_p = head_p;
    struct list_node_s* pred_p = NULL;

    /* Find value */
    while (curr_p != NULL && curr_p->data < value) {
        pred_p = curr_p;
        curr_p = curr_p->next;
    }

    if (curr_p != NULL && curr_p->data == value) {
        if (pred_p == NULL) {
            head_p = curr_p->next;
            free(curr_p);
        } else {
            pred_p->next = curr_p->next;
            free(curr_p);
        }
        return 1;
    } else {
        return 0;
    }
}

