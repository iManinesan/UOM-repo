/* File:     linked_list_rw.c
 *
 * Purpose:  Implement a multi-threaded sorted linked list of 
 *           ints with operations insert, member, delete.  
 *           This version uses read-write locks
 * 
 * Compile:  gcc -g -Wall -o linked_list_rw linkedlist_rw.c -lm -lpthread
 * Run:      ./linked_list_rw <no-of-elements> <no-of-operations> <fraction-of-member> <fraction-of-insert> <fraction-of-delete> <no-of-threads>
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

// Declaration of Functions
int  Insert(int value);
int  Member(int value);
int  Delete(int value);
long getCurrentTime(void);
void initialize(int);
int generateRandom(void);
void* doOperations(void*);

// Variables
struct list_node_s* head_p = NULL;
pthread_rwlock_t rwlock;
int noOfOperationsPerThread;
int noOfMemberPerThread;
int noOfInsertPerThread;
int noOfDeletePerThread;


/*
* Main method to execute the program.
*/
int main(int arc, char *argv[]) {
    // Validate the arguments
    if (arc != 7) {
        printf("Invalid number of arguments %d\n", arc);
        return -1;
    }
    // Variables
    long start, finish, elapsed;
    long thread;
    pthread_t* threadHandles;

    // Collect and interpret the arguments
    int noOfVariables = atoi(argv[1]);
    int noOfThreads = atoi(argv[6]);
    noOfOperationsPerThread = atoi(argv[2]) / noOfThreads;
    noOfMemberPerThread = strtod(argv[3], NULL) * noOfOperationsPerThread;
    noOfInsertPerThread = strtod(argv[4], NULL) * noOfOperationsPerThread;
    noOfDeletePerThread = strtod(argv[5], NULL) * noOfOperationsPerThread;

    threadHandles = (pthread_t*) malloc (noOfThreads * sizeof(pthread_t));
    pthread_rwlock_init(&rwlock, NULL);

    // Initialize the linked list
    initialize(noOfVariables);

    // Get the starting time
    start = getCurrentTime();

    // Do the operations
    for (thread = 0; thread < noOfThreads; thread++) {
        pthread_create(&threadHandles[thread], NULL, doOperations, (void*)thread);
    }

    for (thread = 0; thread < noOfThreads; thread++) {
        pthread_join(threadHandles[thread], NULL);
    }

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
    int inserted = 0;
    int i;
    for (i = 0; i < noOfVariables; i++) {
        inserted = Insert(generateRandom());
        if (!inserted) {
            i--;
        }
    }
}

/*-----------------------------------------------------------------*/
/* Function:    Do Operations
 * Purpose:     Call the operation functions 
 */
void* doOperations(void* rank) {
    long start = ((long) rank) * noOfOperationsPerThread;
    long end = start + noOfOperationsPerThread;

    long i;
    for (i = start; i < end; i++) {
        if (i < start + noOfInsertPerThread) {
            int value = generateRandom();
            pthread_rwlock_wrlock(&rwlock);
            Insert(value);
            pthread_rwlock_unlock(&rwlock);
        } else if (i < start + noOfInsertPerThread + noOfDeletePerThread) {
            int value = generateRandom();
            pthread_rwlock_wrlock(&rwlock);
            Delete(value);
            pthread_rwlock_unlock(&rwlock);
        } else {
            int value = generateRandom();
            pthread_rwlock_rdlock(&rwlock);
            Member(value);
            pthread_rwlock_unlock(&rwlock);
        }
    }

    return NULL;
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
