#include <stdlib.h>
#include <stdio.h>
#include "sorting.h"

@implementation Sorting

+ (NSArray*)selection_sort:(NSArray*)arr {
	NSMutableArray* sorted = [arr mutableCopy];
	for (int i = 0; i < [sorted count]; i++) {
		NSInteger min_index = i;
		for (int j = min_index + 1; j < [sorted count]; j++) {
			if ([[sorted objectAtIndex:j] compare:[sorted objectAtIndex:min_index]] == NSOrderedAscending) {
				min_index = j;
			}
		}
		NSNumber* tmp = [sorted objectAtIndex:i];
		[sorted replaceObjectAtIndex:i withObject:[sorted objectAtIndex:min_index]];
		[sorted replaceObjectAtIndex:min_index withObject:tmp];
	}
	return [sorted copy];
}

+ (NSArray*)insertion_sort:(NSArray*)arr {
	NSMutableArray* inplace = [arr mutableCopy];
	for (int i = 1; i < [inplace count]; i++) {
		int j = i - 1;
		for (; j >= 0; j--) {
			if ([[inplace objectAtIndex:i] compare:[inplace objectAtIndex:j]] != NSOrderedAscending) {
				break;
			}
		}
		[inplace insertObject:[inplace objectAtIndex:i] atIndex:j + 1];
		[inplace removeObjectAtIndex:i + 1];
	}
	return [inplace copy];
}

+ (NSArray*)merge_sort:(NSArray*)arr {
	if ([arr count] < 2) {
		return arr;
	}
	NSArray* left = [Sorting merge_sort:[arr subarrayWithRange:NSMakeRange(0, [arr count] / 2)]];
	NSArray* right = [Sorting merge_sort:[arr subarrayWithRange:NSMakeRange([arr count] / 2, [arr count] - [arr count] / 2)]];

	NSMutableArray* sorted = [NSMutableArray arrayWithCapacity:[arr count]];
	int a = 0;
	int b = 0;
	while (a < [left count] && b < [right count]) {
		if ([[left objectAtIndex:a] compare:[right objectAtIndex:b]] != NSOrderedDescending) {
			[sorted addObject:[left objectAtIndex:a]];
			a++;
		} else {
			[sorted addObject:[right objectAtIndex:b]];
			b++;
		}
	}
	for (; a < [left count]; a++) {
		[sorted addObject:[left objectAtIndex:a]];
	}
	for (; b < [right count]; b++) {
		[sorted addObject:[right objectAtIndex:b]];
	}
	return [sorted copy];
}

+ (NSArray*)quick_sort:(NSArray*)arr {
	if (arr.count < 2) {
		return arr;
	}
	id pivot = [arr objectAtIndex:0];
	NSMutableArray* left = [[NSMutableArray alloc] init];
	NSMutableArray* right = [[NSMutableArray alloc] init];
	for (int i = 1; i < arr.count; i++) {
		if ([[arr objectAtIndex:i] compare:pivot] != NSOrderedDescending) {
			[left addObject:[arr objectAtIndex:i]];
		} else {
			[right addObject:[arr objectAtIndex:i]];
		}
	}
	NSMutableArray* sorted = [NSMutableArray arrayWithCapacity:arr.count];
	[sorted addObjectsFromArray:[Sorting quick_sort:left]];
	[sorted addObject:pivot];
	[sorted addObjectsFromArray:[Sorting quick_sort:right]];
	return [sorted copy];
}

@end

int main() {
	NSAutoreleasePool* pool = [[NSAutoreleasePool alloc] init];
	int digits[] = {1, 6, 1, 8, 0, 3, 3, 9, 8, 8, 7, 4, 9, 8, 9, 4, 8, 4, 8, 2, 0, 4, 5};
	NSMutableArray* unsorted = [[NSMutableArray alloc] init];
	for (int i = 0; i < sizeof(digits) / sizeof(digits[0]); i++) {
		[unsorted addObject:[NSNumber numberWithInt:digits[i]]];
	}
	NSArray* selection_sorted = [Sorting selection_sort:unsorted];
	NSArray* insertion_sorted = [Sorting insertion_sort:unsorted];
	NSArray* merge_sorted = [Sorting merge_sort:unsorted];
	NSArray* quick_sorted = [Sorting quick_sort:unsorted];
	NSLog(@"Unsorted:         %@", unsorted);
	NSLog(@"Selection sorted: %@", selection_sorted);
	NSLog(@"Insertion sorted: %@", insertion_sorted);
	NSLog(@"Merge sorted:     %@", merge_sorted);
	NSLog(@"Quick sorted:     %@", quick_sorted);
	[pool drain];
	return 0;
}