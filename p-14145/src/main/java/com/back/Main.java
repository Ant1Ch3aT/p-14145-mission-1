package com.back;

import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Wise[] wiseList = new Wise[0];
        int nextId = 1;

        while(true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();
            if (cmd.equals("종료")) {
                sc.close();
                return;
            }

            if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = sc.nextLine();
                System.out.print("작가 : ");
                String author = sc.nextLine();
                Wise wiseEntry = new Wise(nextId, content, author);
                wiseList = append(wiseList, wiseEntry);
                System.out.println(nextId + "번 명언이 등록되었습니다.");
                ++nextId;
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("-----------------");

                for(int i = wiseList.length - 1; i >= 0; --i) {
                    Wise wiseEntry = wiseList[i];
                    System.out.println(wiseEntry.id + " / " + wiseEntry.writer + " / " + wiseEntry.content);
                }
            } else if (cmd.startsWith("삭제?id=")) {
                int id = parseId(cmd);
                int index = findIndexById(wiseList, id);
                if (index == -1) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else {
                    wiseList = removeByIndex(wiseList, index);
                    System.out.println(id + "번 명언이 삭제되었습니다.");
                }
            } else if (cmd.startsWith("수정?id=")) {
                int id = parseId(cmd);
                int index = findIndexById(wiseList, id);
                if (index == -1) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                } else {
                    Wise target = wiseList[index];
                    System.out.println("명언(기존) : " + target.content);
                    System.out.print("명언 : ");
                    target.content = sc.nextLine();
                    System.out.println("작가(기존) : " + target.writer);
                    System.out.print("작가 : ");
                    target.writer = sc.nextLine();
                }
            }
        }
    }

    private static Wise[] append(Wise[] arr, Wise wiseEntry) {
        Wise[] newArr = new Wise[arr.length + 1];

        for(int i = 0; i < arr.length; ++i) {
            newArr[i] = arr[i];
        }

        newArr[arr.length] = wiseEntry;
        return newArr;
    }

    private static Wise[] removeByIndex(Wise[] arr, int removeIndex) {
        Wise[] newArr = new Wise[arr.length - 1];
        int newIndex = 0;

        for(int i = 0; i < arr.length; ++i) {
            if (i != removeIndex) {
                newArr[newIndex] = arr[i];
                ++newIndex;
            }
        }

        return newArr;
    }

    private static int findIndexById(Wise[] arr, int id) {
        for(int i = 0; i < arr.length; ++i) {
            if (arr[i].id == id) {
                return i;
            }
        }

        return -1;
    }

    private static int parseId(String cmd) {
        String idStr = cmd.substring(cmd.indexOf(61) + 1);
        return Integer.parseInt(idStr);
    }

    static class Wise {
        int id;
        String content;
        String writer;

        Wise(int id, String content, String author) {
            this.id = id;
            this.content = content;
            this.writer = author;
        }
    }
}
