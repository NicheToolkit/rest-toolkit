package io.github.nichetoolkit.rest.worker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <code>PasswordWorker</code>
 * <p>The type password worker class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class PasswordWorker {
    /**
     * <code>LOWER_REGEX</code>
     * {@link java.lang.String} <p>The constant <code>LOWER_REGEX</code> field.</p>
     * @see java.lang.String
     */
    private static final String LOWER_REGEX = "[a-z]";
    /**
     * <code>UPPER_REGEX</code>
     * {@link java.lang.String} <p>The constant <code>UPPER_REGEX</code> field.</p>
     * @see java.lang.String
     */
    private static final String UPPER_REGEX = "[A-Z]";
    /**
     * <code>NUMBER_REGEX</code>
     * {@link java.lang.String} <p>The constant <code>NUMBER_REGEX</code> field.</p>
     * @see java.lang.String
     */
    private static final String NUMBER_REGEX = "[0-9]";
    /**
     * <code>password</code>
     * {@link java.lang.String} <p>The <code>password</code> field.</p>
     * @see java.lang.String
     */
    private final String password;
    /**
     * <code>length</code>
     * {@link java.lang.Integer} <p>The <code>length</code> field.</p>
     * @see java.lang.Integer
     */
    private final Integer length;
    /**
     * <code>upperSize</code>
     * {@link java.lang.Integer} <p>The <code>upperSize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer upperSize;
    /**
     * <code>lowerSize</code>
     * {@link java.lang.Integer} <p>The <code>lowerSize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer lowerSize;
    /**
     * <code>numSize</code>
     * {@link java.lang.Integer} <p>The <code>numSize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer numSize;
    /**
     * <code>charSize</code>
     * {@link java.lang.Integer} <p>The <code>charSize</code> field.</p>
     * @see java.lang.Integer
     */
    private Integer charSize;

    /**
     * <code>PasswordWorker</code>
     * <p>Instantiates a new password worker.</p>
     * @param password {@link java.lang.String} <p>The password parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public PasswordWorker(String password){
        this.password = password.replaceAll("\\s", "");
        this.length = password.length();
    }

    /**
     * <code>lengthQuest</code>
     * <p>The quest method.</p>
     * @return boolean <p>The quest return object is <code>boolean</code> type.</p>
     */
    public boolean lengthQuest() {
        return lengthQuest(8,16);
    }

    /**
     * <code>lengthQuest</code>
     * <p>The quest method.</p>
     * @param min int <p>The min parameter is <code>int</code> type.</p>
     * @param max int <p>The max parameter is <code>int</code> type.</p>
     * @return boolean <p>The quest return object is <code>boolean</code> type.</p>
     */
    public boolean lengthQuest(int min, int max) {
        /* 密码长度 8-16位 */
        return this.length >= min && this.length <= max;
    }

    /**
     * <code>contentQuest</code>
     * <p>The quest method.</p>
     * @return boolean <p>The quest return object is <code>boolean</code> type.</p>
     */
    public boolean contentQuest() {
        return (getUpperSize() + getLowerSize()) > 0 && getNumSize() > 0;
    }

    /**
     * <code>getUpperSize</code>
     * <p>The upper size getter method.</p>
     * @return {@link java.lang.Integer} <p>The upper size return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getUpperSize() {
        if (this.upperSize == null) {
            this.upperSize = upperMatch(this.password);
        }
        return this.upperSize;
    }

    /**
     * <code>getLowerSize</code>
     * <p>The lower size getter method.</p>
     * @return {@link java.lang.Integer} <p>The lower size return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getLowerSize() {
        if (this.lowerSize == null) {
            this.lowerSize = lowerMatch(this.password);
        }
        return this.lowerSize;
    }

    /**
     * <code>getNumSize</code>
     * <p>The num size getter method.</p>
     * @return {@link java.lang.Integer} <p>The num size return object is <code>Integer</code> type.</p>
     * @see java.lang.Integer
     */
    public Integer getNumSize() {
        if (this.numSize == null) {
            this.numSize = numberMatch(this.password);
        }
        return this.numSize;
    }

    /**
     * <code>getCharSize</code>
     * <p>The char size getter method.</p>
     * @return int <p>The char size return object is <code>int</code> type.</p>
     */
    public int getCharSize() {
        if (this.charSize == null) {
            this.charSize = this.length - this.getUpperSize()
                    -this.getLowerSize() - this.getNumSize();
        }
        return this.charSize;
    }

    /**
     * <code>upperMatch</code>
     * <p>The match method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The match return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    public Integer upperMatch(String target) {
        return match(UPPER_REGEX,target);
    }

    /**
     * <code>lowerMatch</code>
     * <p>The match method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The match return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    public Integer lowerMatch(String target) {
        return match(LOWER_REGEX,target);
    }

    /**
     * <code>numberMatch</code>
     * <p>The match method.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The match return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
    public Integer numberMatch(String target) {
        return match(NUMBER_REGEX,target);
    }

    /**
     * <code>match</code>
     * <p>The method.</p>
     * @param regex  {@link java.lang.String} <p>The regex parameter is <code>String</code> type.</p>
     * @param target {@link java.lang.String} <p>The target parameter is <code>String</code> type.</p>
     * @return {@link java.lang.Integer} <p>The return object is <code>Integer</code> type.</p>
     * @see java.lang.String
     * @see java.lang.Integer
     */
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
