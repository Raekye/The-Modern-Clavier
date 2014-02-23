#!/usr/bin/perl
use strict;

sub selection_sort {
	my @list = @{$_[0]};
	for (my $i = 0; $i < scalar @list; $i++) {
		my $min = $i;
		for (my $j = $i + 1; $j < scalar @list; $j++) {
			if ($list[$j] < $list[$min]) {
				$min = $j;
			}
		}
		@list[$i, $min] = @list[$min, $i];
	}
	return @list;
}

sub insertion_sort {
	my @list = @{$_[0]};
	for (my $i = 1; $i < scalar @list; $i++) {
		my $insert_into = 0;
		for (my $j = $i - 1; $j >= 0; $j--) {
			if ($list[$i] >= $list[$j]) {
				$insert_into = $j + 1;
				last;
			}
		}
		my $removed = splice(@list, $i, 1);
		splice(@list, $insert_into, 0, $removed);
	}
	return @list;
}

sub merge_sort {
	my @list = @{$_[0]};
	if (scalar @list < 2) {
		return @list;
	}
	my @left = @list;#@list[0..(scalar @list) / 2 - 1];
	my @right = splice(@left, (scalar @left) / 2);#@list[(scalar @list) / 2..(scalar @list)];
	@left = merge_sort([@left]);
	@right = merge_sort([@right]);
	my $i = 0;
	my $j = 0;
	my @sorted = ();
	while ($i < scalar @left && $j < scalar @right) {
		if ($left[$i] <= $right[$j]) {
			push(@sorted, $left[$i]);
			$i++;
		} else {
			push(@sorted, $right[$j]);
			$j++;
		}
	}
	for (; $i < scalar @left; $i++) {
		push(@sorted, $left[$i]);
	}
	for (; $j < scalar @right; $j++) {
		push(@sorted, $right[$j]);
	}
	return @sorted;
}

sub quick_sort {
	my @list = @{$_[0]};
	if (scalar @list < 2) {
		return @list;
	}
	my $pivot = $list[0];
	my @left = ();
	my @right = ();
	for (my $i = 1; $i < scalar @list; $i++) {
		if ($list[$i] <= $pivot) {
			push(@left, $list[$i]);
		} else {
			push(@right, $list[$i]);
		}
	}
	@left = quick_sort([@left]);
	@right = quick_sort([@right]);
	my @sorted = (@left, $pivot, @right);
	return @sorted;
}

my @arr = (1, 6, 1, 8, 0, 3, 3, 9, 8, 8, 7, 4, 9, 8, 9, 4, 8, 4, 8, 2, 0, 4, 5);

my @arr_selection_sorted = selection_sort([@arr]);
my @arr_insertion_sorted = insertion_sort([@arr]);
my @arr_merge_sorted = merge_sort([@arr]);
my @arr_quick_sorted = quick_sort([@arr]);

print("Selection sort: @arr_selection_sorted\n");
print("Insertion sort: @arr_insertion_sorted\n");
print("Merge sort:     @arr_merge_sorted\n");
print("Quick sort:     @arr_quick_sorted\n");