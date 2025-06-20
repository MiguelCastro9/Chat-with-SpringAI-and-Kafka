
export const formatTime = (dateString: string | null | undefined): string => {
  if (!dateString) return "---";
  const date = new Date(dateString);
  if (isNaN(date.getTime())) return "Any information";
  return date.toLocaleTimeString("pt-BR", {
    hour: "2-digit",
    minute: "2-digit"
  });
};