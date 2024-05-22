package models;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-05-22T01:12:22")
@StaticMetamodel(AgcFeedback.class)
public class AgcFeedback_ { 

    public static volatile SingularAttribute<AgcFeedback, String> feedback;
    public static volatile SingularAttribute<AgcFeedback, String> classId;
    public static volatile SingularAttribute<AgcFeedback, String> customerId;
    public static volatile SingularAttribute<AgcFeedback, Date> feedbackDate;
    public static volatile SingularAttribute<AgcFeedback, Integer> id;
    public static volatile SingularAttribute<AgcFeedback, String> trainerId;

}