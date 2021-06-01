package com.flp;

public enum Strategy {
    FIXED_RATE{
        @Override
        public SchedulingStrategy getSchedulingStrategy() {
            return new FixedrateScheduling();
        }
    },
    FIXED_INTERVEL
        {
            @Override
            public SchedulingStrategy getSchedulingStrategy() {
                return new FixedScheduling();
            }
        },
    ONCE{
        @Override
        public SchedulingStrategy getSchedulingStrategy() {
            return new ScheduleOnceStrategy();
        }
    };

    public abstract SchedulingStrategy getSchedulingStrategy();
}
