"use client"
import { useEffect, useState } from "react"
import { Button } from "./Button"

export const Counter = () => {
    const [counter, setCounter] = useState(0)
    const [thing, setThing] = useState("")

    useEffect(() => {
        console.log("contador atualizado")
    }, [counter])

    useEffect(() => {
        console.log("coisa atualizado")
    }, [thing])

    return <div className="grid gap-y-4">
        <h2 className="text-2xl">Contador</h2>
        <p>Numero atual: {counter}</p>
        <div className="flex gap-x-2">
            <Button onClick={() => {
                setCounter((providedCounter) => providedCounter - 3);
            }}>-3</Button>
            <Button onClick={() => {
                setCounter((providedCounter) => providedCounter - 1);
            }}>-1</Button>
            <Button onClick={() => {
                setCounter((providedCounter) => providedCounter + 1);
            }}>+1</Button>
            <Button onClick={() => {
                setCounter((providedCounter) => providedCounter + 3);
            }}>+3</Button>
        </div>

        <div className="flex gap-x-2">
            <input className="border border-gray-500 px-4 py-1" type="text" value={thing} onChange={(element) => { setThing(element.target.value) }} />
            <Button onClick={() => {
                setThing("");
            }}>Limpar</Button>
        </div>

    </div>
}