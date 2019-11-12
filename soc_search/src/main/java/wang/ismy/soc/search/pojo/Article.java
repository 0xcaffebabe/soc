package wang.ismy.soc.search.pojo;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author MY
 * @date 2019/11/12 16:51
 */
@Document(indexName = "soc_article",type = "doc")
public class Article implements Serializable {


}
