package operations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOperations {
/*
Stream	return Stream<T>: filter(Predicate<? super T> predicate), limit(long MaxSize),sorted()
		return Optional<T>: findFirst(), max(Comparator<? super T> comparator), min(Comparator<? super T> comparator)
		return T: reduce(T identity, BinaryOperator<T> accumulator)
		return <R> Stream<R>: map(Function<? super T,? extends R> mapper)
		return <R,A> R: collect(Collector<? super T,A,R> collector)
		return boolean: anyMatch(Predicate<? super T> predicate)
		void: forEach()
 */
    public static void main(String [] args){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = integers.stream().reduce(0, (a, b) -> a + b);
        Integer sumBase=IntStream.range(1,6).sum();

        int[] arr =new int[]{1,4,6,8,0};
        List<Integer> list= Arrays.stream(arr).boxed().toList();
        int[] example1 = list.stream().mapToInt(i->i).toArray();//Convert to IntStream, usecases?
        // for converting from primitive stream type
        Stream<Integer> stream2 = IntStream.of(4, 5, 6).boxed();
        Stream<Integer> stream3 = IntStream.of(4, 5, 6).mapToObj(e->e);
        Stream<Object> stream4 = IntStream.of(4, 5, 6).mapToObj(e -> e);
    }
}

