#!/bin/sh

n=100000
sigma=0.03
gamma=0.25
t_values_file_location="/scratch/lustre/home/toru0015/nmmapp1/t_values.out"

mpirun java -jar nmmapp1.jar point-processing $n $sigma $gamma $t_values_file_location