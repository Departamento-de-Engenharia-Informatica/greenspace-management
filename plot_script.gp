set datafile separator ","
set terminal png
set output "execution_times.png"
set xlabel "Input Size"
set ylabel "Execution Time (ms)"
plot "execution_times.csv" using 1:2 with linespoints title "Execution Time vs. Input Size"
