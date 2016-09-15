#!/bin/sh

n=100000
sigma=0.03
gamma=0.25
t_values_file_location="/scratch/lustre/home/toru0015/nmmapp1/t_values.out"
cos_values_file_location="/scratch/lustre/home/toru0015/nmmapp1/cos_values.out"

java -jar nmmapp1.jar plot-creation $n $sigma $gamma $t_values_file_location COS $cos_values_file_location