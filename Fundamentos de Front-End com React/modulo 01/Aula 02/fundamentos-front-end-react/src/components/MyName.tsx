"use client"

import { useParams } from "next/navigation";
import { FC } from "react";

type myNameProps = { name: string; age: number; birthDate: Date };

export const MyName: FC<myNameProps> = ({ name, age, birthDate }) => {
    // props.name === 'Rian' ? <p>Sou o Rian</p> : <p>Sou outra pessoa</p>
    // props.name === 'Rian' && <p>Sou o Rian</p>
    // const {name, age, birthDate} = props;

    const params = useParams();

    console.log(params);

    return <p>Sou o {name}, tenho {age} anos, porque nasci no dia {birthDate.toLocaleDateString("pt-BR")}</p>
}