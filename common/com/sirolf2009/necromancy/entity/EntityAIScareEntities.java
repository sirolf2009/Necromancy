package com.sirolf2009.necromancy.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.IMob;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

public class EntityAIScareEntities extends EntityAIBase {

    public EntityAIScareEntities(EntityLiving entity, float seekingRange, float scaringRange, float speed, Class class1) {
        targets = new ArrayList();
        this.entity = (EntityTeddy) entity;
        this.seekingRange = seekingRange;
        this.scaringRange = scaringRange;
        this.speed = speed;
        targetSpecs = class1;
        pathFinderSelf = entity.getNavigator();
    }

    @Override
    public boolean shouldExecute() {
        if ((entities = entity.worldObj.getEntitiesWithinAABB(targetSpecs, entity.boundingBox.expand(seekingRange, seekingRange, seekingRange))) != null) {
            Iterator it = entities.iterator();
            do {
                if (!it.hasNext()) {
                    break;
                }
                Entity temp = (Entity) it.next();
                if (temp != null && temp instanceof IMob) {
                    targets.add(temp);
                }
            } while (true);
            if (targets != null && entity.entityState == EntityTeddy.EntityState.DEFENDING)
                return true;
        }
        return false;
    }

    @Override
    public void updateTask() {
        if ((target = getClosestEntity()) != null) {
            pathFinderTarget = target.getNavigator();
            pathFinderSelf.tryMoveToEntityLiving(target, entity.getAIMoveSpeed());
            if (entity.getDistanceToEntity(target) < scaringRange) {
                Vec3 var2 = RandomPositionGenerator.findRandomTargetBlockAwayFrom((EntityCreature) target, 16, 7, entity.worldObj.getWorldVec3Pool().getVecFromPool(entity.posX, entity.posY, entity.posZ));
                if (var2 != null && entity.getDistanceSq(var2.xCoord, var2.yCoord, var2.zCoord) >= entity.getDistanceSqToEntity(target)) {
                    pathEntity = pathFinderTarget.getPathToXYZ(var2.xCoord, var2.yCoord, var2.zCoord);
                    if (pathEntity != null) {
                        pathFinderTarget.setPath(pathEntity, 0.4F);
                    }
                }
            }
        }
    }

    @Override
    public boolean continueExecuting() {
        return target != null && entity.entityState == EntityTeddy.EntityState.DEFENDING;
    }

    private EntityLiving getClosestEntity() {
        Iterator iterator = targets.iterator();
        EntityLiving tempEntityTarget = null;
        double tempRange = seekingRange + 1.0F;
        do {
            if (!iterator.hasNext()) {
                break;
            }
            EntityLiving tempEntity = (EntityLiving) iterator.next();
            if (entity.getDistanceToEntity(tempEntity) < tempRange) {
                tempRange = entity.getDistanceToEntity(tempEntity);
                tempEntityTarget = tempEntity;
            }
        } while (true);
        return tempEntityTarget;
    }

    EntityLiving target;
    EntityTeddy entity;
    float seekingRange;
    float scaringRange;
    float speed;
    Class targetSpecs;
    List entities;
    List targets;
    PathNavigate pathFinderSelf;
    PathNavigate pathFinderTarget;
    private PathEntity pathEntity;
}
