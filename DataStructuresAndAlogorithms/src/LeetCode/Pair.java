package LeetCode;

import java.util.Objects;

public class Pair<Integer,String> implements Comparable<Pair<Integer,String> >{
    int key;
    String value;

    Pair(int key,String value){
        this.key=key;
        this.value=value;
    }
    public int getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return key == pair.key && value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public int compareTo(Pair o) {
        return 0;
    }

}

