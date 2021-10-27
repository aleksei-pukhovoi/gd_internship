package string.my_string_implementation;

import java.util.Arrays;

public final class MyString implements Comparable<MyString> {
    private final char[] value;

    public static void main(String[] args) {
        MyString string1 = new MyString(new char[]{'a', 'b', 'c'});
        MyString string2 = new MyString(new char[]{'a', 'b', 'c'});
        System.out.println(string1.equals(string2));
        MyString string3 = string1.concat(string2);
        System.out.println(string3);
        System.out.println(string1.startsWith(new MyString(new char[]{'a'})));
        System.out.println(string3.charAt(1));

    }

    public MyString(char[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public MyString(MyString original) {
        this.value = original.value;
    }

    public MyString() {
        this(new char[]{});
    }

    public MyString(char[] value, int start, int count) {
        if (start < 0) {
            throw new IndexOutOfBoundsException(start);
        }
        if (count <= 0) {
            if (count < 0) {
                throw new IndexOutOfBoundsException(count);
            }
            if (start <= value.length) {
                this.value = new MyString().value;
                return;
            }
        }
        if (start > value.length - count) {
            throw new IndexOutOfBoundsException(start + count);
        }
        this.value = Arrays.copyOfRange(value, start, start + count);
    }

    public MyString concat(MyString str) {
        if (str.length() == 0) {
            return this;
        }
        char[] buf = Arrays.copyOf(value, value.length + str.length());
        System.arraycopy(value, 0, buf, value.length, value.length);
        return new MyString(buf);
    }

    public boolean startsWith(MyString prefix, int index) {
        int length = prefix.length();
        int i = index;
        int j = 0;
        if (index < 0 || index > value.length - length) {
            return false;
        }
        while (--length >= 0) {
            if (charAt(i++) != prefix.charAt(j++)) {
                return false;
            }
        }
        return true;
    }

    public boolean startsWith(MyString prefix) {
        return startsWith(prefix, 0);
    }

    public boolean endsWith(MyString suffix) {
        return startsWith(suffix, value.length - suffix.length());
    }

    public int length() {
        return value.length;
    }

    public char charAt(int index) {
        checkIndex(index);
        return value[index];
    }

    public MyString substring(int start, int end) {
        checkIndex(start, end);
        int length = end - start;
        if (length < 0) {
            throw new IndexOutOfBoundsException(length);
        }
        return ((start == 0) && (end == value.length)) ? this : new MyString(value, start, length);
    }

    public boolean isEmpty() {
        return value.length == 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        MyString anotherString = (MyString) obj;
        return Arrays.equals(value, anotherString.value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char ch : value) {
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public int compareTo(MyString anotherString) {
        int len1 = value.length;
        int len2 = anotherString.length();
        int min = Math.min(len1, len2);
        char[] v1 = value;
        char[] v2 = anotherString.value;

        int index = 0;
        while (index < min) {
            char ch1 = v1[index];
            char ch2 = v2[index];
            if (ch1 != ch2) {
                return ch1 - ch2;
            }
            index++;
        }
        return len1 - len2;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= value.length) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private void checkIndex(int startIndex, int endIndex) {
        if (startIndex < 0) {
            throw new IndexOutOfBoundsException(startIndex);
        }
        if (endIndex >= value.length) {
            throw new IndexOutOfBoundsException(endIndex);
        }
    }
}
