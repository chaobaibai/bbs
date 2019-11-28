package cn.fengyichao.bbs.service;

import cn.fengyichao.bbs.dto.Page;
import cn.fengyichao.bbs.entity.Post;
import cn.fengyichao.bbs.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengyichao
 * @date 2019/11/27 - 14:34
 */
@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public Page<Post> getPostsByPage(Integer pageNum, Integer pageSize){
        Long total = postMapper.getPostTotal();
        Page<Post> page = new Page<>(total,pageSize,pageNum);
        List<Post> posts = postMapper.getPostsByPage((page.getPageNum()-1)*pageSize, pageSize); //这里不能直接传入pageNum,如果pageNum>pageCount时，数据就查不出来
        page.setList(posts);
        return page;
    }

    public Page<Post> getPostsByAuthor(Integer author,Integer pageNum,Integer pageSize) {
        Long totalByAuthor = postMapper.getPostTotalByAuthor(author);
        Page<Post> page = new Page<>(totalByAuthor,pageSize,pageNum);
        List<Post> posts = postMapper.getPostsByAuthorAndPage(author, (page.getPageNum() - 1) * pageSize, pageSize);
        page.setList(posts);
        return page;
    }

    public Post getPostById(Integer id) {
        return postMapper.getPostById(id);
    }
}
