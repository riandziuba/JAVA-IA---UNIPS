"use client"
import { useContext } from "react"
import { CounterContext } from "@/context/CounterContext"
import { Button } from "./Button";

export const CounterGlobal = () => {
    const { counter, setCounter } = useContext(CounterContext)
    return <div className="grid gap-y-4">
        <h2 className="text-2xl">Contador Global</h2>
        <p>Numero atual: {counter}</p>
        <div className="flex gap-x-2">
            <Button onClick={() => {
                setCounter((providedCounter) => (providedCounter ?? 0) - 3);
            }}>-3</Button>
            <Button onClick={() => {
                setCounter((providedCounter) => (providedCounter ?? 0) - 1);
            }}>-1</Button>
            <Button onClick={() => {
                setCounter((providedCounter) => (providedCounter ?? 0) + 1);
            }}>+1</Button>
            <Button onClick={() => {
                setCounter((providedCounter) => (providedCounter ?? 0) + 3);
            }}>+3</Button>
        </div>

    </div>
}