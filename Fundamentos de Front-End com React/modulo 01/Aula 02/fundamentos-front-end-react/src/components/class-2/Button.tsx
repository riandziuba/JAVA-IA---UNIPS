"use client"
import { ButtonHTMLAttributes, FC } from "react"

export const Button: FC<ButtonHTMLAttributes<HTMLButtonElement>> = ({ onClick, children, ...props }) => {
    return (
        <button className="border border-blue-500 p-2 rounded cursor-pointer bg-blue-500 hover:bg-blue-900 text-{#fff} font-bold" onClick={onClick} {...props}>{children}</button>
    )
}