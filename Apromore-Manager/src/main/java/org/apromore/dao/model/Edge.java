/*
 * Copyright © 2009-2016 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package org.apromore.dao.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Edge generated by hbm2java
 */
@Entity
@Table(name = "edge",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"uri"})
        }
)
@Configurable("edge")
@Cache(expiry = 180000, size = 10000, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class Edge implements Serializable {

    private Integer id;
    private String uri;
    private String originalId;
    private Boolean def = false;

    private Expression conditionExpression;

    private Node sourceNode;
    private Node targetNode;
    private Node cancelNode;

    private Set<EdgeMapping> edgeMappings = new HashSet<>();
    private Set<EdgeAttribute> attributes = new HashSet<>();

    /**
     * Public Constructor.
     */
    public Edge() { }



    /**
     * returns the Id of this Object.
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    /**
     * Sets the Id of this Object
     * @param id the new Id.
     */
    public void setId(final Integer id) {
        this.id = id;
    }



    @Column(name = "uri", length = 40)
    public String getUri() {
        return this.uri;
    }

    public void setUri(final String newUri) {
        this.uri = newUri;
    }

    @Column(name = "originalId", length = 200)
    public String getOriginalId() {
        return this.originalId;
    }

    public void setOriginalId(final String newOriginalId) {
        this.originalId = newOriginalId;
    }

    @Column(name = "def", length = 1)
    public Boolean getDef() {
        return this.def;
    }

    public void setDef(final Boolean newDefault) {
        this.def = newDefault;
    }


    @ManyToOne
    @JoinColumn(name = "conditionExpressionId")
    public Expression getConditionExpression() {
        return this.conditionExpression;
    }

    public void setConditionExpression(final Expression newConditionExpression) {
        this.conditionExpression = newConditionExpression;
    }

    @ManyToOne
    @JoinColumn(name = "sourceNodeId")
    public Node getSourceNode() {
        return this.sourceNode;
    }

    public void setSourceNode(final Node newSourceNode) {
        this.sourceNode = newSourceNode;
    }

    @ManyToOne
    @JoinColumn(name = "targetNodeId")
    public Node getTargetNode() {
        return this.targetNode;
    }

    public void setTargetNode(final Node newTargetNode) {
        this.targetNode = newTargetNode;
    }

    @ManyToOne
    @JoinColumn(name = "cancelNodeId")
    public Node getCancelNode() {
        return this.cancelNode;
    }

    public void setCancelNode(final Node newCancelNode) {
        this.cancelNode = newCancelNode;
    }


    @OneToMany(mappedBy = "edge", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<EdgeMapping> getEdgeMappings() {
        return edgeMappings;
    }

    public void setEdgeMappings(Set<EdgeMapping> newEdgeMappings) {
        edgeMappings = newEdgeMappings;
    }

    @OneToMany(mappedBy = "edge", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<EdgeAttribute> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Set<EdgeAttribute> newAttributes) {
        this.attributes = newAttributes;
    }

}


