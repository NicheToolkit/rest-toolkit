package io.github.nichetoolkit.rest.worker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>PasswordWoker</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PasswordWorker {
    private static final String LOWER_REGEX = "[a-z]";
    private static final String UPPER_REGEX = "[A-Z]";
    private static final String NUMBER_REGEX = "[0-9]";
    /** 密码内容 */
    private String password;
    /** 密码长度 */
    private Integer length;
    /** 大写字母长度 */
    private Integer upperSize;
    /** 小写字母长度 */
    private Integer lowerSize;
    /** 数字长度 */
    private Integer numSize;
    /** 特殊字符长度 */
    private Integer charSize;

    public PasswordWorker(String password){
        this.password = password.replaceAll("\\s", "");
        this.length = password.length();
    }

    public boolean mustLengthQuest() {
        /** 密码长度 8-16位 */
        return this.length >= 8 && this.length <= 16;
    }

    public boolean mustContentQuest() {
        /** 包含 大小写字母 数字*/
        return (getUpperSize() + getLowerSize()) > 0 && getNumSize() > 0;
    }

    public Integer getUpperSize() {
        if (this.upperSize == null) {
            this.upperSize = upperMatch(this.password);
        }
        return this.upperSize;
    }

    public Integer getLowerSize() {
        if (this.lowerSize == null) {
            this.lowerSize = lowerMatch(this.password);
        }
        return this.lowerSize;
    }

    public Integer getNumSize() {
        if (this.numSize == null) {
            this.numSize = numberMatch(this.password);
        }
        return this.numSize;
    }

    public int getCharSize() {
        if (this.charSize == null) {
            this.charSize = this.length - this.getUpperSize()
                    -this.getLowerSize() - this.getNumSize();
        }
        return this.charSize;
    }

    public Integer upperMatch(String target) {
        return match(UPPER_REGEX,target);
    }

    public Integer lowerMatch(String target) {
        return match(LOWER_REGEX,target);
    }

    public Integer numberMatch(String target) {
        return match(NUMBER_REGEX,target);
    }

    private Integer match(String regex,String target) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        int length = 0;
        while (matcher.find()) {
            length++;
        }
        return length;
    }
}
