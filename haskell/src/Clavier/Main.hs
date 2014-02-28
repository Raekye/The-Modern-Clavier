import qualified Clavier.SortingAlgorithms
import Data.List

golden_ratio :: Double
golden_ratio = (1 + (sqrt 5)) / 2

-- Alternatively: import Data.Char; (\ x -> (ord x) - (ord 0))
digits :: [Int]
digits = map (\ x -> read [x] :: Int) $ reverse $ foldl' (\ xs x -> if x == '.' then xs else x : xs) "" (show golden_ratio)

main :: IO ()
main = do
	putStr "Original:       "
	print digits
	putStr "Selection sort: "
	print $ Clavier.SortingAlgorithms.selection_sort digits
	putStr "Insertion sort: "
	print $ Clavier.SortingAlgorithms.insertion_sort digits
	putStr "Merge sort:     "
	print $ Clavier.SortingAlgorithms.merge_sort digits
	putStr "Quick sort:     "
	print $ Clavier.SortingAlgorithms.quick_sort digits