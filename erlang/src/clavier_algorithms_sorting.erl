-module(clavier_algorithms_sorting).
-export([main/0, mergesort/2, quicksort/2, insertionsort/2, selectionsort/2]).

main() ->
	io:fwrite("Original:       ~p~n", [golden_ratio_digits()]),
	io:fwrite("Selection sort: ~p~n", [selectionsort(golden_ratio_digits(), fun(A, B) -> A =< B end)]),
	io:fwrite("Insertion sort: ~p~n", [insertionsort(golden_ratio_digits(), fun(A, B) -> A =< B end)]),
	io:fwrite("Merge sort:     ~p~n", [mergesort(golden_ratio_digits(), fun(A, B) -> A =< B end)]),
	io:fwrite("Quick sort:     ~p~n", [quicksort(golden_ratio_digits(), fun(A, B) -> A =< B end)]).

mergesort([], _) -> [];
mergesort([X], _) -> [X];
mergesort(List, Compare) ->
	{Left, Right} = mergesort_partition(List, true, {[], []}),
	mergesort_unpartition(mergesort(Left, Compare), mergesort(Right, Compare), Compare).

mergesort_partition([], _, Aggregate) -> Aggregate;
mergesort_partition([Car | Cdr], IsOdd, {Left, Right}) ->
	{NewLeft, NewRight} =
		if IsOdd -> {[Car | Left], Right};
			true -> {Left, [Car | Right]}
		end,
	mergesort_partition(Cdr, not IsOdd, {NewLeft, NewRight}).

mergesort_unpartition([], Right, _) -> Right;
mergesort_unpartition(Left, [], _) -> Left;
mergesort_unpartition([LeftCar | LeftCdr], [RightCar | RightCdr], Compare) ->
	Side = Compare(LeftCar, RightCar),
	{NewCar, NewLeft, NewRight} =
		if Side -> {LeftCar, LeftCdr, [RightCar | RightCdr]};
			true -> {RightCar, [LeftCar | LeftCdr], RightCdr}
		end,
	[NewCar | mergesort_unpartition(NewLeft, NewRight, Compare)].

quicksort([], _) -> [];
quicksort([X], _) -> [X];
quicksort([Car | Cdr], Compare) ->
	{Left, Right} = quicksort_partition(Cdr, fun(X) -> X < Car end, {[], []}),
	quicksort(Left, Compare) ++ [Car | quicksort(Right, Compare)].

quicksort_partition([], _, Aggregate) -> Aggregate;
quicksort_partition([Car | Cdr], Test, {Left, Right}) ->
	Passed = Test(Car),
	{NewLeft, NewRight} =
		if Passed -> {[Car | Left], Right};
			true -> {Left, [Car | Right]}
		end,
	quicksort_partition(Cdr, Test, {NewLeft, NewRight}).

insertionsort([], _) -> [];
insertionsort([Car | Cdr], Compare) ->
	Helper =
		fun(Helper, Sorted, [First | Rest]) ->
			Helper(Helper, insert_into_sorted_list(First, Sorted, Compare), Rest);
		(_, Sorted, []) ->
			Sorted
		end,
	Helper(Helper, [Car], Cdr).

insert_into_sorted_list(Val, [], _) -> [Val];
insert_into_sorted_list(Val, [Car | Cdr], Compare) ->
	OnLeft = Compare(Val, Car),
	if OnLeft -> [Val | [Car | Cdr]];
		true -> [Car | insert_into_sorted_list(Val, Cdr, Compare)]
	end.

selectionsort([], _) -> [];
selectionsort([X], _) -> [X];
selectionsort([Car | Cdr], Compare) ->
	{First, _, Rest} = selectionsort_separate([Car | Cdr], Compare, Car, 0, 0),
	[First | selectionsort(Rest, Compare)].

selectionsort_separate([], _, First, FirstIndex, _) -> {First, FirstIndex, []};
selectionsort_separate([Car | Cdr], Compare, First, FirstIndex, I) ->
	Ahead = Compare(Car, First),
	{NewFirst, NewFirstIndex} =
		if Ahead -> {Car, I};
			true -> {First, FirstIndex}
		end,
	{FinalFirst, FinalFirstIndex, Rest} = selectionsort_separate(Cdr, Compare, NewFirst, NewFirstIndex, I + 1),
	{FinalFirst, FinalFirstIndex, if I == FinalFirstIndex -> Rest; true -> [Car | Rest] end}.

golden_ratio_digits() ->
	lists:map(fun(X) -> X - $0 end, re:replace(float_to_list((1 + math:sqrt(5)) / 2), "[^0-9]", "", [global,{return,list}])).