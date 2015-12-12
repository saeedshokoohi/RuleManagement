/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to purchase Celerio ? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Template pack-backend-jpa:src/main/java/repository/support/OrderBy.p.vm.java
 */
package repository.support;

import javax.persistence.metamodel.Attribute;
import java.io.Serializable;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

/**
 * Holder class for search ordering used by the {@link SearchParameters}.
 * When used with {@link NamedQueryUtil}, you pass column name. For other usage, pass the property name.
 */
public class OrderBy implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Attribute<?, ?>> attributes;
    private OrderByDirection direction = ASC;

    public OrderBy(OrderByDirection direction, Attribute<?, ?>... attributes) {
        this.direction = checkNotNull(direction);
        this.attributes = newArrayList(checkNotNull(attributes));
    }

    public OrderBy(OrderByDirection direction, String path, Class<?> from) {
        this.direction = checkNotNull(direction);
        this.attributes = MetamodelUtil.getInstance().toAttributes(checkNotNull(path), checkNotNull(from));
    }

    public List<Attribute<?, ?>> getAttributes() {
        return attributes;
    }

    public OrderByDirection getDirection() {
        return direction;
    }

    public boolean isOrderDesc() {
        return DESC == direction;
    }
}