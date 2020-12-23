package com.hc.treet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname TreeNode
 * @Description:
 * @author: Li Xiaobing
 * @date: 2020/12/22 16:36
 */
@Data
@AllArgsConstructor
public class TreeNode<T> {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Long parentId;
    @JsonProperty
    private Integer seq;
    @JsonProperty("label")
    private String name;
    @JsonProperty
    private Integer level;
    @JsonProperty
    private String code;
    @JsonProperty
    private T data;

    @JsonProperty
    private List<TreeNode<T>> children;

    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }

    public TreeNode(Long id, Long parentId, Integer seq, String name) {
        this.id = id;
        this.parentId = parentId;
        this.seq = seq;
        this.name = name;
    }

    public void appendChild(TreeNode<T> child) {
        if (children == null) {
            children = new ArrayList<>();
        }
        int pos = binaryFindSeqPosition(child);

        children.add(pos, child);
    }

    private int binaryFindSeqPosition(TreeNode<T> targetNode) {
        if (children.size() == 0 || targetNode.seq < children.get(0).seq) {
            return 0;
        }
        if (children.get(children.size() - 1).seq < targetNode.seq) {
            return children.size();
        }

        int targetSeq = targetNode.seq;
        int lo = 0;
        int hi = children.size();
        int mid;

        while (lo < hi) {
            mid = lo + ((hi - lo) >> 1);
            if (children.get(mid).seq <= targetSeq) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof TreeNode) {
            TreeNode otherNode = (TreeNode) obj;
            return id.equals(otherNode.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id == null ? 0 : id.hashCode());
        return result;
    }
}
