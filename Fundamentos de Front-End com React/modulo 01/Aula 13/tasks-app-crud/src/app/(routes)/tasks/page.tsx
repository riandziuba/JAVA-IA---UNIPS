import { FormTasks } from "@/src/components/forms/FormTasks";
import { fetchWithToken } from "@/src/lib/fetchWithToken";
import { Metadata } from "next";
import { cookies } from "next/headers";
import { handleCompleteTask, handleCreateTask, handleDeleteTask } from "./actions";
import { TaskCard } from "@/src/components/TaskCard";

const PAGE_TITLE = "Tasks"


export const metadata: Metadata = {
  title: "Tasks"
}

type TaskType =
  {
    "_id": string,
    "userId": string,
    "title": string,
    "completed": boolean,
    "deleted": boolean,
    "createDate": string,
    "modifyDate": string,
    "__v": number
  }


export default async function Tasks() {
  const cookieStore = await cookies();
  const token = cookieStore.get("token")?.value;

  const { tasks }: { tasks: TaskType[] } = await fetchWithToken(
    `${process.env.BACKEND_URL}/tasks`,
    token!,
    {
      next: {
        tags: ["get-tasks"],
      }
    })

  return (
    <>
      <h1 className="text-4xl text-center font-bold">{PAGE_TITLE}</h1>
      <FormTasks action={handleCreateTask} />
      <ul className="grid gap-y-3">
        {tasks.reverse().sort((a, b) => {
          if (a.completed && !b.completed) return 1;
          if (!a.completed && b.completed) return -1;
          return 0;
        }).map((task) => (
          <TaskCard
            key={task._id}
            id={task._id}
            completeAction={handleCompleteTask}
            completed={task.completed}
            deleteAction={handleDeleteTask}
          >
            {task.title}
          </TaskCard>
        ))}
      </ul>
    </>
  );
}
