package io.github.nichetoolkit.rest;

import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>RestPurview</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface RestPurview extends RestValue<Long,String> {

    Long getPurview();

    @SuppressWarnings("Duplicates")
    static <T extends RestPurview> T parsePurview(Class<T> clazz, Long purview) {
        if (purview != null && clazz.isEnum()) {
            Map<Long,T> valueEnumMap = Stream.of(clazz.getEnumConstants()).collect(Collectors.toMap(RestPurview::getPurview, Function.identity()));
            return valueEnumMap.get(purview);
        } else {
            return null;
        }
    }

    static boolean reachKey(@NonNull Long key, @NonNull RestPurview purviewType) {
        Long sourceKey = purviewType.getKey();
        return (key & sourceKey) != 0;
    }

    static <T extends RestPurview> Long annexKey(Collection<T> purviewTypes) {
        return annexKey(0L,purviewTypes);
    }


    @SuppressWarnings("unchecked")
    static <T extends RestPurview> Long annexKey(T... purviewTypes) {
        return annexKey(0L,purviewTypes);
    }

    @SuppressWarnings("unchecked")
    static <T extends RestPurview> Long annexKey(Long purview, T... purviewTypes) {
        if (purviewTypes == null || purviewTypes.length == 0) {
            return purview;
        }
        return annexKey(purview,Arrays.asList(purviewTypes));
    }


    static <T extends RestPurview> Long annexKey(Long purview, Collection<T> purviewTypes) {
        Long key = purview;
        if (purviewTypes != null && purviewTypes.size() > 0) {
            for (T purviewType : purviewTypes) {
                Long sourceKey = purviewType.getKey();
                key = key | sourceKey;
            }
        }
        return key;
    }


}
