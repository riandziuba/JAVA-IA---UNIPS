"use client"
import { FC, useActionState, useEffect, useState } from "react";
import { FormError } from "../FormError";

type FormTasksProps = {
  action: (_: string, formData: FormData) => Promise<string>
}

export const FormTasks: FC<FormTasksProps> = ({ action }) => {
  const [task, setTask] = useState("");
  const [errorMessage, formAction, isPending] = useActionState(action, "")

  useEffect(() => {
    if (!isPending && !errorMessage) {
      setTask("");
    }
  }, [isPending, errorMessage]);
  return (
    <>
      {
        !isPending && (
          <FormError message={errorMessage}></FormError>
        )
      }
      <form className="relative shadow-lg rounded-lg" action={formAction}>
        <input
          className="w-full px-2 py-1 pr-10 text-[#7b7c7b] border border-[#e8e9e9] focus:border-[#b1b2b2] hover:border-[#b1b2b2] outline-none rounded-l-lg"
          name="task"
          value={task}
          onChange={(e) => setTask(e.target.value)}
          placeholder="Informe o titulo da tasks"
        />
        <button className="absolute top-0 right-0 bottom-0 px-3 bg-[#141516] text-white cursor-pointer shadow-md rounded-r-lg" disabled={isPending}>
          +
        </button>
      </form>
    </>
  );
}
