package algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BfsDemo {
    public static void main(String[] args) {
        List<Integer>[] adjacencyList = new ArrayList[7];
        boolean[] isVisited = new boolean[7];

        for (int i = 0; i < adjacencyList.length; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        adjacencyList[0].add(1); adjacencyList[0].add(2); adjacencyList[0].add(3);
        adjacencyList[1].add(0); adjacencyList[1].add(4); adjacencyList[1].add(5);
        adjacencyList[2].add(0); adjacencyList[2].add(6);
        adjacencyList[3].add(0); adjacencyList[3].add(6);
        adjacencyList[4].add(1);
        adjacencyList[5].add(1);
        adjacencyList[6].add(2); adjacencyList[6].add(3);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        isVisited[0] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.println("访问" + (currentVertex + 1));

            for (int i = 0; i < adjacencyList[currentVertex].size(); i++) {
                int neighborIndex = adjacencyList[currentVertex].get(i);
                if (!isVisited[neighborIndex]) {
                    queue.add(neighborIndex);
                    isVisited[neighborIndex] = true;
                }
            }
        }
    }
}
