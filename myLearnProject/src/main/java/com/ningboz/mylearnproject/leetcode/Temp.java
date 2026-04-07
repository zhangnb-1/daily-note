package com.ningboz.mylearnproject.leetcode;

public class Temp {
    public static void main(String[] args) {
        int x =1;
        func1(x);
        System.out.println("main:"+x);
        x = func2(x);
        System.out.println("main:"+x);

        User user1 = new User("101", "zs", 18);
        func3(user1);
        System.out.println(user1.getAge());
    }
    public static void func1(int x){
        x = x+1;
        System.out.println("func1:"+x);
    }

    public static int func2(int x){
        x = x+1;
        System.out.println("func2:"+x);
        return x;
    }
    public static void func3(User user){
        user = new User("102","ls",33);
//        user.setAge(20);
    }

    static class User{
        private String id;
        private String name;
        private int age;

        public User(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
