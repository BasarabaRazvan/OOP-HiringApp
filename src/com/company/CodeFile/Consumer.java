package com.company.CodeFile;

import com.company.ExceptionFile.ResumeIncompleteException;

import java.util.*;

public abstract class Consumer {
    public Resume r;
    ArrayList<Consumer> consumers;

    public Consumer() {
        r = null;
        consumers = new ArrayList<>();
    }

    public Consumer(Resume r, ArrayList<Consumer> consumers) {
        this.r = r;
        this.consumers = consumers;
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public static class Resume {
        private Information information;
        private Vector<Education> educations;
        private  Vector<Experience> experiences;

        private Resume(ResumeBuilder builder) {
            this.information = builder.information;
            this.educations = builder.educations;
            this.experiences = builder.experiences;
        }

        public Information getInformation() {
            return information;
        }

        public Vector<Education> getEducations() {
            return educations;
        }

        public Vector<Experience> getExperiences() {
            return experiences;
        }

        public void setExperiences(Vector<Experience> experiences) {
            this.experiences = experiences;
        }

        @Override
        public String toString() {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            for(Education e : educations)
                s1.append(e).append("\n");
            if(experiences != null) {
                for (Experience ex : experiences)
                    s2.append(ex).append("\n");
            }
            return "Information:\n" + information.toString() +
                    "\nEducations\n" + s1 +
                    "\nExperiences\n" + s2;
        }

        public static class ResumeBuilder {
            private Information information;
            private Vector<Education> educations;
            private Vector<Experience> experiences;

            public ResumeBuilder(Information information) throws ResumeIncompleteException {
                if(information == null)
                    throw new ResumeIncompleteException("Resume incomplet!");
                else
                    this.information = information;
            }

            public ResumeBuilder educations(Vector<Education> educations) throws ResumeIncompleteException {
                if(educations == null)
                    throw new ResumeIncompleteException("Resume incomplet!");
                else {
                    this.educations = educations;
                    return this;
                }
            }

            public ResumeBuilder experiences(Vector<Experience> experiences) {
                this.experiences = experiences;
                return this;
            }

            public Resume build() {
                return new Resume(this);
            }
        }
    }

    public void add(Education education) {
        if(r.getEducations().contains(education))
            return;
        r.getEducations().add(education);
        Collections.sort(r.getEducations());
    }

    public void add(Experience experience) {
        if(r.getExperiences() == null) {
            Vector<Experience> e = new Vector<>();
            e.add(experience);
            r.setExperiences(e);
            return;
        }
        if(r.getExperiences().contains(experience))
            return;
        r.getExperiences().add(experience);
        Collections.sort(r.getExperiences());
    }

    public void add(Consumer consumer) {
        if(consumers == null) {
            ArrayList<Consumer> c = new ArrayList<>();
            c.add(consumer);
            setConsumers(c);
        }
        if(consumers.contains(consumer))
            return;
        consumers.add(consumer);
    }

    //pentru a stii cand sa trec la nivelul urmator, am folosit o coada in care bagam lista de
    //consumeri ai consumerilor de pe nivelul pe care ma aflu, iar la final puneam null.
    public int getDegreeInFriendship(Consumer consumer) {
        Map<Consumer, Boolean> map = new HashMap<>();
        map.put(this, true);
        Queue<Consumer> queue = new LinkedList<>();
        int level = 0;
        queue.add(this);
        queue.add(null);
        while (!queue.isEmpty()) {
            Consumer c = queue.poll();
            if(c == null) {
                if(queue.peek() != null) {
                    queue.add(null);
                }
                level += 1;
            } else {
                if(c.equals(consumer))
                    return level;
                if(c.consumers != null) {
                    for(Consumer aux : c.consumers) {
                        if(!map.containsKey(aux)) {
                            queue.add(aux);
                            map.put(aux,true);
                        }
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public void remove(Consumer consumer) {
        if(consumers.contains(consumer)) {
            consumers.remove(consumer);
            if(consumer.consumers != null)
                consumer.consumers.remove(this);
        }
    }

    //am verificat daca data de sfarsit e diferita de null si daca nivelul educatie e "college", caz
    //in care returnam anul.
    public Integer getGraduationYear() {
        if(r.getEducations().isEmpty())
            return 0;
        for (Education e : r.getEducations()) {
            if(e.dataDeSfarsit != null && e.nivelEducatie.equals("college")) {
                Vector<String> data;
                data = e.obtinereData(e.dataDeSfarsit);
                return Integer.parseInt(data.get(2));
            }
        }
        return 0;
    }

    public Double meanGPA() {
        double total = 0;
        int count = 0;
        for(Education e : r.getEducations()) {
            if(e.mediaDeFinalizare != null) {
                total += e.mediaDeFinalizare;
                count++;
            }
        }
        return total / count;
    }

    public int totalExperience() {
        int total = 0;
        if(r.getExperiences() != null) {
            for (Experience e : r.getExperiences())
                total += e.aniExperienta();
        }
        return total;
    }
}

