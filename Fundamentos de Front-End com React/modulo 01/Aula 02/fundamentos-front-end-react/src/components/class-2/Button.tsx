"use client"
import { FC, MouseEventHandler, ReactNode } from "react"

type buttonProps = {
    onClick: MouseEventHandler<HTMLButtonElement>;
    children: ReactNode
}

export const Button: FC<buttonProps> = ({ onClick, children }) => {
    return (
        <button className="border border-blue-500 px-4 py-1 rounded cursor-pointer bg-blue-500 hover:bg-blue-900 text-{#fff} font-bold" onClick={onClick}>{children}</button>
    )
}