package LeetCode;

import java.util.Stack;

public class Simplify_Path_071 {
/*
You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.
The rules of a Unix-style file system are as follows:
    A single period '.' represents the current directory.
    A double period '..' represents the previous/parent directory.
    Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
    Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:
    The path must start with a single slash '/'.
    Directories within the path must be separated by exactly one slash '/'.
    The path must not end with a slash '/', unless it is the root directory.
    The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.
Input: path = "/.../a/../b/c/../d/./"   Output: "/.../b/d"
 */
    public static void main(String[] args){
        Simplify_Path_071 smp= new Simplify_Path_071();
        String in1 = "/home//foo/";
        String in2 ="/.../a/../b/c/../d/./";
        System.out.println(smp.simplifyPath(in1));
        System.out.println(smp.simplifyPathAccepted(in1));
        System.out.println(smp.simplifyPath(in2));
        System.out.println(smp.simplifyPathAccepted(in2));
    }

    public String simplifyPath(String path) {
        Character pre='/';
        Stack <Character> stack=new Stack<>();
        for (int i=1; i<path.length();i++) {
            //   "/" ".." "." "ch"
            Character ch= path.charAt(i);
            if (ch == '/'){
                if (pre=='/'){
                    stack.push(ch);
                }
                if (Character.isLetterOrDigit(pre)){

                }
                pre = ch;
            }
            if (Character.isLetterOrDigit(ch)) {
                if (pre == '/' ) {
                    stack.push(pre);
                    stack.push(ch);
                }
                if (Character.isLetterOrDigit(pre)) {
                    stack.push(ch);
                }
                pre = ch;
            }
            if (ch=='.') {
                if (pre == '.') {
                        Character character=stack.pop();
                        while (character!='/'){
                            character =stack.pop();
                        }
                }
                if (Character.isLetterOrDigit(pre)) {
                    stack.push(pre);
                    pre = ch;
                }
            }
        }
        StringBuilder sb= new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public String simplifyPathAccepted(String path) {
        String[] components = path.split("/");
        Stack<String> st = new Stack<>();
        for (String comp : components) {
            if (comp.equals("") || comp.equals(".")) {
                continue;
            }
            if (comp.equals("..")) {
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else {
                st.push(comp);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.insert(0, "/" + st.pop());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

}
