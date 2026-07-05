package xml;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "beans")
public class Beans {

    private List<Bean> beans;

    @XmlElement(name = "bean")
    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }

    public static class Bean {
        private String id;
        private String className;

        @XmlAttribute
        public String getId() { return id; }

        public void setId(String id) { this.id = id; }

        @XmlAttribute(name = "class")
        public String getClassName() { return className; }

        public void setClassName(String className) { this.className = className; }
    }
}