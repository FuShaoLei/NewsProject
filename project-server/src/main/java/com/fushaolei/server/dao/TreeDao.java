package com.fushaolei.server.dao;

import com.fushaolei.server.bean.Tree;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeDao {
    List<Tree> findTree(int id);
}
