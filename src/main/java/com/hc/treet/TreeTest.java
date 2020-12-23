package com.hc.treet;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TreeTest
 * @Description:
 * @author: Li Xiaobing
 * @date: 2020/12/23 9:05
 */
public class TreeTest {
    public static void main(String[] args) throws IOException {
        test1();
    }

    public static void test1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<TreeNode<Integer>> treeList = getTreeList();
        System.out.println(treeList.size());
        long begin = System.currentTimeMillis();
        Tree<Integer> tree = Tree.createTree(treeList);
        long end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000.0 + "s");

        System.out.println("-------------序列化------------");
        begin = System.currentTimeMillis();
        String s = objectMapper.writeValueAsString(tree);
        end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000.0 + "s");

        System.out.println("-------------反序列化------------");
        begin = System.currentTimeMillis();
        tree = objectMapper.readValue(s, Tree.class);
        end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000.0 + "s");
    }

    public static List<TreeNode<Integer>> getTreeList() {
        List<TreeNode<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            long iId = i + 1L;
            TreeNode<Integer> iTreeNode = new TreeNode<>(iId, 0L, i, "node-" + (i + 1));
            list.add(iTreeNode);
            for (int j = 0; j < 10; j++) {
                long jId = iId * 10 + j + 1;
                TreeNode<Integer> jTreeNode = new TreeNode<>(jId, iId, j, iTreeNode.getName() + "-" + (j + 1));
                list.add(jTreeNode);
                for (int k = 0; k < 100; k++) {
                    long kid = jId * 10 + k + 1;
                    TreeNode<Integer> kTreeNode = new TreeNode<>(kid, jId, k, jTreeNode.getName() + "-" + (k + 1));
                    list.add(kTreeNode);
                }
            }
        }

        return list;
    }
}
