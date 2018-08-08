package com.lam.spritework.Allocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AllocationPool handles reuse of IPoolObjects.
 */
public class AllocationPool {
    private static AllocationPool instance;
    
    private Map<String, PoolType> types = new HashMap<>();

    private AllocationPool() {
    }

    public static synchronized AllocationPool getInstance() {
        if (instance == null) {
            instance = new AllocationPool();
        }
        return instance;
    }

    public void addType(Class c, Integer initial, Integer grow) {
        String typeName = c.getSimpleName();
        
        if (!instance.types.containsKey(typeName)) {
            types.put(typeName, new PoolType(typeName, c, initial, grow));
        }
    }

    public void removeType(String typeName) {
        if (instance.types.containsKey(typeName)) {
            instance.types.get(typeName).clear();
            instance.types.remove(typeName);
        }
    }

    public void resetType(String typeName) {
        if (!instance.types.containsKey(typeName)) {
            types.get(typeName).reset();
        }
    }

    public void clearTypes(String typeName) {
        for (PoolType p : types.values()) {
            removeType(p.getName());
        }
    }

    /**
     * allocate create or reuse an object.
     *
     * @param typeName the SpriteEffect type to return
     * @return The allocated Sprite or null on error
     */
    public Object allocate(String typeName) {
        if (instance.types.containsKey(typeName)) {
            return types.get(typeName).allocate();
        }
        return null;
    }

    /**
     * release moves the given Object onto the inactive list for
     * later usage
     *
     * @param obj being released
     * @return true if added back into the available list
     */
    public void release(Object obj) {
        String className = obj.getClass().getSimpleName();
        if (instance.types.containsKey(className)) {
            instance.types.get(className).release(obj);
        };
    }

    /**
     * PoolType internal class which manages a specific type of SpriteEffect.
     */
    private class PoolType {
        private String name;
        private Class clazz;
        private Integer initial;
        private Integer grow;
        private List<Object> active = new ArrayList<>();
        private List<Object> available = new ArrayList<>();

        PoolType(String typeName, Class clazz, Integer initial, Integer grow) {
            this.name = typeName;
            this.clazz = clazz;
            this.initial = initial;
            this.grow = grow;

            reset();
        }

        public String getName() {
            return name;
        }

        public Object allocate() {
            if (available.isEmpty()) {
                more(grow);
            }

            Object obj = available.get(0);
            active.add(obj);
            available.remove(0);
            return obj;
        }

        public void release(Object obj) {
            active.remove(obj);
            available.add(obj);
        }

        public void reset() {
            clear();
            more(initial);
        }

        public void clear() {
            active.clear();
            available.clear();
        }

        private void more(int amount) {
            Object obj;
            try {
                for (int i = 0; i < grow; ++i) {
                    obj = clazz.newInstance();
                    available.add(obj);
                };
            } catch (Exception e) {
                // TODO: Handle Exception
            }
        }
    }
}
