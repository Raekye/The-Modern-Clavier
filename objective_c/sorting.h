#ifndef __SORTING_H_
#define __SORTING_H_

#include <Foundation/Foundation.h>

@interface Sorting

+ (NSArray*) selection_sort:(NSArray*)arr;
+ (NSArray*) insertion_sort:(NSArray*)arr;
+ (NSArray*) merge_sort:(NSArray*)arr;
+ (NSArray*) quick_sort:(NSArray*)arr;

@end

#endif // SORTING_H_