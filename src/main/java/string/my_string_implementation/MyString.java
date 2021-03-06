package string.my_string_implementation;

import java.util.Arrays;

public final class MyString implements Comparable<MyString> {
    private final char[] value;

    public MyString(char[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    public MyString(MyString original) {
        this.value = Arrays.copyOf(original.value, original.value.length);
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

    public MyString replace (char oldChar, char newChar) {
        if(oldChar != newChar) {
            MyString string = new MyString(this);
            for (int i=0; i<string.length(); i++) {
                if(string.value[i] == oldChar) {
                    string.value[i] = newChar;
                }
            }
            return string;
        }
        return this;
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

        int index = 0;
        while (index < min) {
            char ch1 = value[index];
            char ch2 = anotherString.value[index];
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
