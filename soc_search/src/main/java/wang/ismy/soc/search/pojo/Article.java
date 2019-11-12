package wang.ismy.soc.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author MY
 * @date 2019/11/12 16:51
 */
@Document(indexName = "soc_article",type = "doc")
@Data
public class Article implements Serializable {

    @Id
    private String id;

    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String title;

    @Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String content;

    @Field(index = false,store = true,type = FieldType.Keyword)
    private String state;
}
