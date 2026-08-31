import { PropsWithChildren } from "react";

export const FormButton = ({ children }: PropsWithChildren) => (
  <button className="py-2 bg-[#141516] text-white cursor-pointer shadow-md rounded-lg hover:shadow-none">{children}</button>
);
