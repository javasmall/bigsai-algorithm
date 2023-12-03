package algorithm;

import java.util.Arrays;

public class EightQueens {
    static int allNum = 0; //总数

    public static void main(String[] args) {
        boolean[] rows = new boolean[8]; // 行
        boolean[] cols = new boolean[8]; // 列
        boolean[] leftDiagonals = new boolean[15]; // 左斜
        boolean[] rightDiagonals = new boolean[15]; //右斜
        int[][] chessboard = new int[8][8]; // 棋盘
        solveQueens(0, rows, cols, leftDiagonals, rightDiagonals, chessboard);
    }

    private static void solveQueens(int rowIndex, boolean[] rows, boolean[] cols,
                                    boolean[] leftDiagonals, boolean[] rightDiagonals, int[][] chessboard) {
        if (rowIndex == 8) {
            allNum++;
            printChessboard(chessboard); // 使用更具体的函数名
        } else {
            for (int colIndex = 0; colIndex < 8; colIndex++) {
                if (!rows[rowIndex] && !cols[colIndex] &&
                        !leftDiagonals[rowIndex + colIndex] && !rightDiagonals[rowIndex + (7 - colIndex)]) {
                    rows[rowIndex] = true;
                    cols[colIndex] = true;
                    leftDiagonals[rowIndex + colIndex] = true;
                    rightDiagonals[rowIndex + (7 - colIndex)] = true;
                    chessboard[rowIndex][colIndex] = 1;
                    solveQueens(rowIndex + 1, rows, cols, leftDiagonals, rightDiagonals, chessboard);
                    rows[rowIndex] = false;
                    cols[colIndex] = false;
                    leftDiagonals[rowIndex + colIndex] = false;
                    rightDiagonals[rowIndex + (7 - colIndex)] = false;
                    chessboard[rowIndex][colIndex] = 0;
                }
            }
        }
    }

    private static void printChessboard(int[][] chessboard) {
        System.out.println("第" + allNum + "个排列为");
        for (int[] row : chessboard) {
            System.out.println(Arrays.toString(row));
        }
    }
}
