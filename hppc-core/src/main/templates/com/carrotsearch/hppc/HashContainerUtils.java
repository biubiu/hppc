package com.carrotsearch.hppc;

final class HashContainerUtils
{
    /**
     * Maximum capacity for an array that is of power-of-two size and still
     * allocable in Java (not a negative int).  
     */
    final static int MAX_CAPACITY = 0x80000000 >>> 1;
    
    /**
     * Minimum capacity for a hash container.
     */
    final static int MIN_CAPACITY = 4;

    /**
     * Default capacity for a hash container.
     */
    final static int DEFAULT_CAPACITY = 16;

    /**
     * Default load factor.
     */
    final static float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Round the capacity to the next allowed value. 
     */
    static int roundCapacity(int requestedCapacity)
    {
        if (requestedCapacity > MAX_CAPACITY)
            return MAX_CAPACITY;

        return Math.max(MIN_CAPACITY, BitUtil.nextHighestPowerOfTwo(requestedCapacity));
    }

    /**
     * Return the next possible capacity, counting from the current buffers'
     * size.
     */
    static int nextCapacity(int current)
    {
        assert current > 0 && Long.bitCount(current) == 1 : "Capacity must be a power of two.";

        if (current < MIN_CAPACITY / 2)
        { 
            current = MIN_CAPACITY / 2;
        }

        current <<= 1;
        if (current < 0) 
        {
            throw new RuntimeException("Maximum capacity exceeded.");
        }

        return current;
    }
}
