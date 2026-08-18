"use client"
import { createContext, Dispatch, ReactNode, SetStateAction, useEffect, useState } from 'react';

type CounterType = number | null

type CounterContextType = {
    counter: CounterType,
    setCounter: Dispatch<SetStateAction<CounterType>>
};

export const CounterContext = createContext<CounterContextType>({
    counter: 0,
    setCounter: () => { },
});

export default function CounterProvider({ children }: { children: ReactNode }) {
    const [counter, setCounter] = useState<CounterType>(null);
    // session storage
    // useEffect(() => {
    //     const sessionStorageCounter = sessionStorage.getItem('counter') ?? 0;

    //     if (sessionStorageCounter !== null) {
    //         setCounter(+sessionStorageCounter)
    //     }
    // }, [])


    // useEffect(() => {
    //     if (counter) sessionStorage.setItem('counter', counter.toString());
    // }, [counter])

    useEffect(() => {
        const localStorageCounter = localStorage.getItem('counter') ?? 0;

        if (localStorageCounter !== null) {
            setCounter(+localStorageCounter)
        }
    }, [])


    useEffect(() => {
        if (counter != null) localStorage.setItem('counter', counter.toString());
    }, [counter])


    return <CounterContext.Provider value={{
        counter, setCounter
    }}>
        {children}
    </CounterContext.Provider >
}