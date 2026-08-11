"use client"
import { useContext } from "react"
import { CounterContext } from "@/context/CounterContext"

export const CounterGlobalValue = () => {
    const { counter } = useContext(CounterContext)
    return <div className="grid gap-y-4">
        <p>Valor do contador global: {counter}</p>
    </div>
}