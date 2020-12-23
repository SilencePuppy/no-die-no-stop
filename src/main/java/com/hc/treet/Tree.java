package com.hc.treet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Classname Tree
 * @Description:
 * @author: Li Xiaobing
 * @date: 2020/12/22 17:48
 */
@AllArgsConstructor
@NoArgsConstructor
public class Tree<T> {
    private static final List EMPTY_NODE_LIST = new ArrayList(0);

    private static final Long ROOT_ID = 0L;
    private static final String TREE_NAME = "Tree";

    @JsonProperty
    private TreeNode<T> root = new TreeNode<>(ROOT_ID, null, null, TREE_NAME);

    private List<TreeNode<T>> readyToInserts = EMPTY_NODE_LIST;
    private List<TreeNode<T>> readyToUpdates = EMPTY_NODE_LIST;
    private List<TreeNode<T>> readyToDeletes = EMPTY_NODE_LIST;

    public TreeNode<T> getRoot() {
        return root;
    }

    /**
     * @return com.hc.treet.Tree<T>
     * @Description 将一个节点列表结合转化为对应的树结构
     * @Param [nodes] 待处理节点集合
     * @author Li Xiaobing
     * @date 2020/12/22 18:28
     **/
    public static <T> Tree<T> createTree(List<TreeNode<T>> nodes) {
        final Integer BUFFER_DEFAULT_CAPACITY = 10;
        HashMap<Long, TreeNode<T>> buffer = new HashMap<>(nodes.size() <= 100 ? BUFFER_DEFAULT_CAPACITY : nodes.size() / 5);

        Tree<T> tree = new Tree<>();
        TreeNode<T> root = tree.getRoot();
        buffer.put(root.getId(), root);

        for (TreeNode<T> node : nodes) {
            TreeNode<T> pNode = buffer.get(node.getParentId());
            if (pNode != null) {
                pNode.appendChild(node);
            } else if (ROOT_ID.equals(node.getParentId())) {// 判断是否是根节点孩子
                root.appendChild(node);
            }
            buffer.put(node.getId(), node);
        }

        return tree;
    }

    public static <T> Tree<T> createTree2(List<TreeNode<T>> nodes){
        Tree<T> tree =new Tree<>();

        Map<Long, List<TreeNode<T>>> collect = nodes.stream().collect(Collectors.groupingBy(TreeNode::getParentId));
        for (TreeNode<T> node : nodes) {
            List<TreeNode<T>> treeNodes = collect.get(node.getId());
            if (treeNodes != null) {
                node.setChildren(treeNodes);
            }
            if (node.getParentId() == 0) {
                tree.getRoot().appendChild(node);
            }
        }

        return tree;
    }
}
