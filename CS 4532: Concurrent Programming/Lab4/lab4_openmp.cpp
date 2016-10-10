#include <iostream>
#include <cstdlib>
#include <ctime>
#include <cmath>
#include <omp.h>
#include <iostream>
#include <fstream>
#include <cctype>
#include <cstring>


using namespace std;

double matrixA[1000][1000];
double matrixB[1000][1000];
double matrixC[1000][1000];
double transposeB[1000][1000];
double matrixD[1000][1000];

void populateMatrix(int n);
double multiplySerial(int n);
double multiplyParallel(int n);
double multiplyParallelImproved(int n);
void transpose(int n);
void check(int n);

int main(){

	double results[3][385] = {0};
	int n = 0;

	ofstream outputFile;
	outputFile.open ("results.txt");
	for (int r=0; r<10; r++){
		n+= 100;
		int sum,avg =0;
		for (int k=0; k<385 ; k++){
			populateMatrix(n);
			transpose(n);
			results[0][k] = multiplySerial(n);
			results[1][k] = multiplyParallel(n);
			results[2][k] = multiplyParallelImproved(n);
			//check(n);
		}

		outputFile << "Results for n = "<< (r+1)*100 << endl;
		for (int k = 0; k<3; k++){
			sum= 0;
			for (int l=0; l<385; l++){
				sum += results[k][l];
				// outputFile << results[k][l] <<endl;
			}
			avg = sum / 385;
			outputFile << avg <<endl;
			outputFile << endl <<endl;
		}
	}

	outputFile.close();
	return 0;
}

void populateMatrix(int n){

	for (int i=0; i<n; i++){
		for (int j=0; j<n; j++){
			matrixA[i][j] = rand();
			matrixB[i][j] = rand();
		}
	}
}

void transpose(int n) {
    int i,j;
    for(i=0; i<n; i++) {
        for(j=0; j<n; j++) {
            transposeB[i][j] = matrixB[j][i];
        }
    }
}

//Serial multiplication of Matrix A and B
double multiplySerial (int n){
	double start = omp_get_wtime();
	int a,b,c;
	for (a = 0; a < n; a++){
		for (b = 0; b < n; b++){
			double sum = 0;
			for (c = 0; c < n; c++){
				sum += matrixA[a][c] * matrixB[c][b];
			}
			matrixC[a][b] = sum;
		}

	}

	double end = omp_get_wtime();
	//cout << endl << "Time for Serial Multiplication: " << end-start << endl;
	return (end-start) * 1000;

}

//Parallel multiplication of Matrix A and B
double multiplyParallel(int n){
	//Static Scheduler
	memset(	matrixD,0,sizeof	matrixD);
	int a,b,c;
	double start=omp_get_wtime();
	#pragma omp parallel for schedule(static,50) collapse(2) private(a,b,c) shared(matrixA,matrixB,matrixD)
	for (a = 0; a < n; a++){
		for (b = 0; b < n; b++){
			double sum = 0;
			for (c = 0; c < n; c++){
				sum += matrixA[a][c] * matrixB[c][b];
			}
			matrixD[a][b] = sum;
		}

	}
	double end=omp_get_wtime();
	//cout << endl << "Time for Parallel Multiplication: " << end-start << endl;
	return (end-start) * 1000;
}

//Improved parallel multiplication of Matrix A and B
double multiplyParallelImproved(int n){
	//Dynamic Scheduler
	memset(	matrixD,0,sizeof matrixD);
	int a,b,c;
	transpose(n);
	double start = omp_get_wtime();
	#pragma omp parallel for schedule(dynamic,50) collapse(2) private(a,b,c) shared(matrixA,matrixB,matrixD)
	for (a = 0; a < n; a++){
		for (b = 0; b < n; b++){
			double sum = 0;
			for (c = 0; c < n; c++){
				sum += matrixA[a][c] * transposeB[b][c];
			}
			matrixD[a][b] = sum;
		}

	}
	double end = omp_get_wtime();
	//cout << endl << "Time for Parallel Multiplication: " << end-start << endl;
	return (end-start) * 1000;
}

//Check if Serial Result and Parallel result are same for accuracy of parallelism
void check(int n){
	for (int a=0; a < n; a++){
		for (int b=0; b<n; b++){
			if (matrixC[a][b] != matrixD[a][b]){
				cout << "Invalid" <<endl;
				return ;
			}
		}
	}
}
