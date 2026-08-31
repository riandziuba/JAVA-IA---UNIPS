"use client"
import classNames from "classnames";
import { FC, PropsWithChildren } from "react";

interface TaskCardProps extends PropsWithChildren {
  id: string;
  completeAction?: (formData: FormData) => Promise<void>;
  completed?: boolean;
  deleteAction?: (formData: FormData) => Promise<void>;
}

export const TaskCard: FC<TaskCardProps> = ({ id, completeAction, completed, deleteAction, children }) => (
  <li className={
    classNames(
      "p-4 grid grid-cols-[auto_1fr_auto] gap-x-4 items-center text-[#7b7c7b] border border-[#e8e9e9] rounded-lg",
      { "opacity-50": completed, "hover:border-[#b1b2b2]": !completed })}>
    <form action={completeAction} className="flex">
      <input name="id" type="hidden" value={id} />
      <input type="checkbox" className="accent-[#141516]" name="completed" defaultChecked={completed} onChange={(e) => { e.target.form?.requestSubmit(); }} />
    </form>
    <p
      className={classNames("cursor-default", { "line-through": completed })}
    >{children}</p>

    {!completed && (
      <form action={deleteAction} className="flex">
        <input name="id" type="hidden" value={id} />
        <button className="cursor-pointer hover:[&_svg_path]:stroke-red-500">
          <svg
            className="size-3"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 14 14"
          >
            <path
              className="stroke-red-700"
              strokeLinecap="round"
              strokeLinejoin="round"
              strokeWidth="2"
              d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
            ></path>
          </svg>
        </button>
      </form>
    )}
  </li >
);
